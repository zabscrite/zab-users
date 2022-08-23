package com.zab.zabusers;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.zab.zabusers")
class LayerDependencyArchTest {

    @ArchTest
    public static final ArchRule layersShouldAccessOnlyUpperOnes =
            layeredArchitecture().consideringAllDependencies()
                    .layer("Controller").definedBy("..controller..")
                    .layer("Domain").definedBy("..domain..")
                    .whereLayer("Controller").mayOnlyBeAccessedByLayers("Controller")
                    .whereLayer("Domain").mayOnlyBeAccessedByLayers("Controller", "Domain");

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