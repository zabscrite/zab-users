package com.zab.zabusers;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.*;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@AnalyzeClasses(packages = "com.zab.zabusers")
class ControllerArchTest {

    private static final DescribedPredicate<JavaClass> residesInControllerPackage = resideInAPackage("..controller");

    private static final ArchCondition<JavaMethod> haveAllParamsValidated =
            new ArchCondition<JavaMethod>("have all parameters annotated with @Valid") {
                @Override
                public void check(JavaMethod method, ConditionEvents events) {
                    List<Set<JavaAnnotation<JavaParameter>>> parameterAnnotations = method.getParameterAnnotations();
                    if(!parameterAnnotations.isEmpty() && !containsValidAnnotation(parameterAnnotations))
                    {
                        String message = String.format("Method %s annotated with @Valid", method.getFullName());
                        events.add(SimpleConditionEvent.violated(method, message));
                    }
                }

                private boolean containsValidAnnotation(List<Set<JavaAnnotation<JavaParameter>>> parameterAnnotations) {
                    return parameterAnnotations.stream().anyMatch(javaAnnotations -> {
                        List<String> annotationClassNames = javaAnnotations.stream()
                                .map(javaAnnotation -> javaAnnotation.getType().getName())
                                .collect(Collectors.toList());
                        return annotationClassNames.contains(Valid.class.getName());
                    });
                }
            };

    @ArchTest
    public static final ArchRule controllersShouldHaveNamesEndingController =
            classes().that().areAnnotatedWith(RestController.class)
                    .should().haveSimpleNameEndingWith("Controller");
    @ArchTest
    public static final ArchRule controllersShouldResideInAControllerPackage =
            classes().that().areAnnotatedWith(RestController.class)
                    .should().resideInAPackage("..controller");

    @ArchTest
    public static final ArchRule controllersShouldBeAnnotatedWithRestController =
            classes().that(residesInControllerPackage)
                    .and().haveSimpleNameEndingWith("Controller")
                    .should().beAnnotatedWith(RestController.class);

    @ArchTest
    public static final ArchRule publicControllerMethodsShouldBeAnnotatedWithRequestMethodMappings =
            methods().that().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
                    .and().arePublic()
                    .should().beAnnotatedWith(GetMapping.class)
                    .orShould().beAnnotatedWith(PostMapping.class)
                    .orShould().beAnnotatedWith(PutMapping.class)
                    .orShould().beAnnotatedWith(DeleteMapping.class);

    @ArchTest
    public static final ArchRule publicControllerMethodParametersAreValidated =
            methods().that().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
                    .and().arePublic()
                    .should(haveAllParamsValidated);

}