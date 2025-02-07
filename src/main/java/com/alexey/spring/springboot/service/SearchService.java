package com.alexey.spring.springboot.service;

import com.alexey.spring.springboot.domain.entity.InventoryItem;
import com.alexey.spring.springboot.repository.InventoryItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Service
public class SearchService {

    private static final Logger log = LoggerFactory.getLogger(SearchService.class);
    private final InventoryItemRepository repository;

    public SearchService(InventoryItemRepository repository) {
        this.repository = repository;
    }

    public List<InventoryItem> searchByFilter(String filter) {
        log.info("Received filter: {}", filter);
        System.out.println("START searchByFilter with filter: " + filter);
        Specification<InventoryItem> spec = parseFilter(filter);
        System.out.println("Final Specification: " + spec);
        return repository.findAll(spec);
    }

    private Specification<InventoryItem> parseFilter(String filter) {
        if (filter == null || filter.trim().isEmpty()) {
            return Specification.where(null);
        }

        filter = filter.trim();
        System.out.println("Parsing filter: " + filter);
        List<Boolean> isOrList = new ArrayList<>();
        List<String> parts = splitExpression(filter, isOrList);
        System.out.println("After splitExpression: " + parts);
        System.out.println("isOrList values: " + isOrList);
        List<Specification<InventoryItem>> conditions = new ArrayList<>();

        if (parts.size() == 1 && !parts.get(0).contains("||") && !parts.get(0).contains("&")) {
            return createSpecification(parts.get(0));
        }

        for (int i = 0; i < parts.size(); i++) {
            String part = removeOuterBrackets(parts.get(i));
            System.out.println("Processing part after removing brackets: " + part);

            if (!part.contains("||") && !part.contains("&") && part.contains("=")) {
                Specification<InventoryItem> spec = createSpecification(part);
                if (spec != null) {
                    conditions.add(spec);
                }
            } else {
                Specification<InventoryItem> spec = parseFilter(part);
                if (spec != null) {
                    conditions.add(spec);
                }
            }
        }

        Specification<InventoryItem> spec = conditions.get(0);
        for (int i = 1; i < conditions.size(); i++) {
            if (isOrList.get(i - 1)) {
                System.out.println("Applying OR between: " + conditions.get(i - 1) + " and " + conditions.get(i));
                spec = spec.or(conditions.get(i));
            } else {
                System.out.println("Applying AND between: " + conditions.get(i - 1) + " and " + conditions.get(i));
                spec = spec.and(conditions.get(i));
            }
        }

        return spec;
    }

    private String removeOuterBrackets(String expression) {
        while (expression.startsWith("(") && expression.endsWith(")")) {
            expression = expression.substring(1, expression.length() - 1);
        }
        return expression;
    }

    private List<String> splitExpression(String expression, List<Boolean> isOrList) {
        List<String> parts = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        StringBuilder currentPart = new StringBuilder();
        boolean insideBrackets = false;

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == '(') {
                stack.push(ch);
                insideBrackets = true;
            } else if (ch == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                insideBrackets = !stack.isEmpty();
            }

            if ((ch == '&' || (ch == '|' && i + 1 < expression.length() && expression.charAt(i + 1) == '|'))
                    && stack.isEmpty() && !insideBrackets) {
                parts.add(currentPart.toString().trim());
                isOrList.add(ch == '|');
                System.out.println("Split at position " + i + " with operator " + (ch == '|' ? "||" : "&"));
                currentPart.setLength(0);
                if (ch == '|') i++;
            } else {
                currentPart.append(ch);
            }
        }

        if (currentPart.length() > 0) {
            parts.add(currentPart.toString().trim());
        }
        System.out.println("splitExpression result: " + parts);
        return parts;
    }

    private Specification<InventoryItem> createSpecification(String condition) {
        if (!condition.contains("=")) {
            log.warn("Invalid condition: {}", condition);
            return null;
        }

        String[] parts = condition.split("=");
        if (parts.length != 2) {
            log.warn("Skipping invalid filter condition: {}", condition);
            return null;
        }

        String fieldName = parts[0].trim();
        String value = parts[1].trim();

        System.out.println("Creating Specification for " + fieldName + " = " + value);
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get(fieldName)),
                        criteriaBuilder.lower(criteriaBuilder.literal("%" + value + "%"))
                );
    }

    public List<InventoryItem> searchByWord(Map<String, String> filters) {
        String keyword = filters.keySet().stream().findFirst().orElse("").toLowerCase();

        Specification<InventoryItem> spec = (root, query, criteriaBuilder) -> {
            String likeKeyword = "%" + keyword + "%";

            return root.getModel().getDeclaredSingularAttributes().stream()
                    .filter(attr -> attr.getJavaType() == String.class)
                    .map(attr -> criteriaBuilder.like(criteriaBuilder.lower(root.get(attr.getName())), likeKeyword))
                    .reduce(criteriaBuilder::or)
                    .orElse(null);
        };

        return repository.findAll(spec);
    }
}
