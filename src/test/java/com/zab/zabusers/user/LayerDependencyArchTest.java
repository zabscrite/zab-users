package com.zab.zabusers.user;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "com.zab.zabusers")
class LayerDependencyArchTest {

    @ArchTest
    public static final ArchRule domainPackageShouldNotAccessControllerPackage =
            noClasses().that().resideInAPackage("..domain..")
                    .should().dependOnClassesThat().resideInAPackage("..controller..");

    @ArchTest
    public static final ArchRule repositoriesShouldNotAccessServices =
            noClasses().that().haveNameMatching(".*Repository")
                    .should().dependOnClassesThat().haveNameMatching(".*Service");

    @ArchTest
    public static final ArchRule repositoriesShouldNotAccessControllers =
            noClasses().that().haveNameMatching(".*Repository")
                    .should().dependOnClassesThat().haveNameMatching(".*Controller");
}