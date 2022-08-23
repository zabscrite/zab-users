package com.zab.zabusers;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.zab.zabusers")
public class ComponentDependencyArchTest {

    private static final String JWT_COMPONENT = "com.zab.zabusers.shared.auth.jwt..";
    private static final String TEAM_COMPONENT = "com.zab.zabusers.team..";

    private static final DescribedPredicate<JavaClass> inJwtComponent = resideInAPackage(JWT_COMPONENT);
    private static final DescribedPredicate<JavaClass> inTeamComponent = resideInAPackage(TEAM_COMPONENT);


    @ArchTest
    public static final ArchRule jwtComponentDependency =
            noClasses().that().resideOutsideOfPackage(JWT_COMPONENT)
                    .should().dependOnClassesThat(inJwtComponent);
    @ArchTest
    public static final ArchRule teamComponentDependency =
            classes().that(inTeamComponent)
                    .should().onlyHaveDependentClassesThat(inTeamComponent.or(inJwtComponent))
                    .as("Team component should only be accessed by JWT");
}
