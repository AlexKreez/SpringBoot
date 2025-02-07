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
        List<String> parts = splitExpression(filter);
        System.out.println("After splitExpression: " + parts);
        List<Specification<InventoryItem>> andConditions = new ArrayList<>();

        for (String part : parts) {
            part = removeOuterBrackets(part);
            System.out.println("Processing part after removing brackets: " + part);
            if (part.contains("||")) {
                List<String> orParts = List.of(part.split("\\|\\|"));
                System.out.println("Splitting OR conditions: " + orParts);
                Specification<InventoryItem> orSpec = Specification.where(null);
                for (String orPart : orParts) {
                    Specification<InventoryItem> parsedSpec = parseFilter(orPart.trim());
                    orSpec = (orSpec == null) ? parsedSpec : orSpec.or(parsedSpec);
                }
                andConditions.add(orSpec);
            } else if (part.contains("&")) {
                List<String> andParts = List.of(part.split("&"));
                System.out.println("Splitting AND conditions: " + andParts);
                Specification<InventoryItem> andSpec = Specification.where(null);
                for (String andPart : andParts) {
                    Specification<InventoryItem> parsedSpec = parseFilter(andPart.trim());
                    andSpec = (andSpec == null) ? parsedSpec : andSpec.and(parsedSpec);
                }
                andConditions.add(andSpec);
            } else {
                Specification<InventoryItem> spec = createSpecification(part.trim());
                System.out.println("Creating Specification for condition: " + part);
                andConditions.add(spec);
            }
        }

        Specification<InventoryItem> spec = Specification.where(null);
        for (Specification<InventoryItem> andSpec : andConditions) {
            spec = (spec == null) ? andSpec : spec.and(andSpec);
        }

        return spec;
    }

    private String removeOuterBrackets(String expression) {
        if (expression.startsWith("(") && expression.endsWith(")")) {
            return expression.substring(1, expression.length() - 1);
        }
        return expression;
    }

    private List<String> splitExpression(String expression) {
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
                stack.pop();
                insideBrackets = !stack.isEmpty();
            }


            if ((ch == '&' || (ch == '|' && i + 1 < expression.length() && expression.charAt(i + 1) == '|'))
                    && stack.isEmpty() && !insideBrackets) {
                parts.add(currentPart.toString().trim());
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
//package com.alexey.spring.springboot.service;
//
//import com.alexey.spring.springboot.domain.entity.InventoryItem;
//import com.alexey.spring.springboot.repository.InventoryItemRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class SearchService {
//
//    private static final Logger log = LoggerFactory.getLogger(SearchService.class);
//    private final InventoryItemRepository repository;
//
//    public SearchService(InventoryItemRepository repository) {
//        this.repository = repository;
//    }
//
//    public List<InventoryItem> searchByFilter(String filter) {
//        log.info("Received filter: {}", filter);
//        Specification<InventoryItem> spec = parseFilter(filter);
//
//        log.debug("Final Specification: {}", spec);
//        return repository.findAll(spec);
//    }
//
//    private Specification<InventoryItem> parseFilter(String filter) {
//        System.out.println(filter);
//        if (filter == null || filter.trim().isEmpty()) {
//            return Specification.where(null);
//        }
//
//        filter = filter.replaceAll("[()]", "").trim();
//        System.out.println("BEZ SKOBOK:       " + filter);// Убираем скобки
//        if (!filter.contains("||") && !filter.contains("&")) {
//            System.out.println("SMTH WRONG?????");
//            return createSpecification(filter);
//        }
//
//        // Разбираем условия с "||" и "&"
//        Specification<InventoryItem> spec = Specification.where(null);
//        String[] orConditions = filter.split("\\|\\|");
//        System.out.println("SPLITED WITH ||     " + Arrays.toString(orConditions));
//        for (String orCondition : orConditions) {
//            System.out.println(orCondition + " awdawdaaaaaaaaaaaaaaaaaaa");
//            Specification<InventoryItem> orSpec = Specification.where(null);
//            String[] andConditions = orCondition.split("&");
//            System.out.println("ANDCONDITION:    " + Arrays.toString(andConditions));
//            for (String condition : andConditions) {
//                Specification<InventoryItem> fieldSpec = createSpecification(condition);
//                orSpec = (orSpec == null) ? fieldSpec : orSpec.and(fieldSpec);
//            }
//            spec = (spec == null) ? orSpec : spec.or(orSpec);
//        }
//        System.out.println(spec);
//        return spec;
//    }
//
//    private Specification<InventoryItem> createSpecification(String condition) {
//        System.out.println("CONDITION: " + condition);
//        if (!condition.contains("=")) {
//            log.warn("Invalid condition: {}", condition);
//            return null;
//        }
//
//        String[] parts = condition.split("=");
//        if (parts.length != 2) {
//            log.warn("Skipping invalid filter condition: {}", condition);
//            return null;
//        }
//
//        String fieldName = parts[0].trim();
//        String value = parts[1].trim().toLowerCase();
//        System.out.println();
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.like(criteriaBuilder.lower(root.get(fieldName)), "%" + value + "%");
//    }
//
//    public List<InventoryItem> searchByWord(Map<String, String> filters) {
//        String keyword = filters.keySet().stream().findFirst().orElse("").toLowerCase();
//
//        Specification<InventoryItem> spec = (root, query, criteriaBuilder) -> {
//            String likeKeyword = "%" + keyword + "%";
//
//            return root.getModel().getDeclaredSingularAttributes().stream()
//                    .filter(attr -> attr.getJavaType() == String.class)
//                    .map(attr -> criteriaBuilder.like(criteriaBuilder.lower(root.get(attr.getName())), likeKeyword))
//                    .reduce(criteriaBuilder::or)
//                    .orElse(null);
//        };
//
//        return repository.findAll(spec);
//    }
//}
