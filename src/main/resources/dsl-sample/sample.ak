level Elementary {
    title: "Elementary"
    value:1
}

level Weak {
    title:"Weak"
    value:2
    competence: [Weak:60%]
}

level Moderate {
    title:"Moderate"
    value:3
    competence: [Weak:75%, Moderate: 60%]
}

level Good {
    title:"Good"
    value:4
    competence: [Weak:85%, Moderate: 75%, Good:60%]
}

level Great {
    title:"Great"
    value:5
    competence: [Weak:95%, Moderate: 85%, Good:75%, Great: 60%]
}


attribute SoftwareReliability {
    title: "Software-Reliability"
    description: ""
    subject: Software
}

attribute SoftwareEfficiency {
    title: "Software-Efficiency"
    description: ""
    subject: Software
}

attribute SoftwareMaintainability  {
    title: "Software-Maintainability "
    description: ""
    subject: Software
}

attribute SoftwareUsability  {
    title: "Software-Usability "
    description: ""
    subject: Software
}

attribute TeamAgileWorkflow {
    title: "Team-Agile Workflow"
    description: ""
    subject: Team
}

attribute TeamPerformanceStability {
    title: "Team-Performance Stability"
    description: ""
    subject: Team
}

questionnaire CleanArchitecture {
	title: "Clean Architecture"
	description: ""
}

questionnaire CodeQuality {
	title: "Code Quality"
	description: ""
}

questionnaire SoftwareQualityTest {
	title: "Software Quality Test"
	description: ""
}

questionnaire SoftwareQualityTunning {
	title: "Software Quality Tunning"
	description: ""
}

questionnaire DevOps {
	title: "DevOps"
	description: ""
}

questionnaire CodeLifecycle {
	title: "Code Life Cycle"
	description: ""
}

questionnaire Technology {
	title: "Technology"
	description: ""
}

questionnaire UiUx {
	title: "UI/UX"
	description: ""
}

questionnaire ApiAndIntegration {
	title: "API and Integration"
	description: ""
}

questionnaire Methodology {
	title: "Methodology"
	description: ""
}

questionnaire TeamLearning {
	title: "Team Learning"
	description: ""
}


questionnaire LogAndMonitoring {
	title: "Log and Monitoring"
	description: ""
}

questionnaire QualityConsequences {
	title: "Quality Consequences"
	description: ""
}

question m1 {
    questionnaire: ApiAndIntegration
    title: "Is API provided using standard and well-known communication protocols (such as REST and GraphQL)?"
    options: "No", "Yes"
	affects SoftwareMaintainability on level Weak with values [0.0, 1.0]

}

question m2 {
    questionnaire: ApiAndIntegration
    title: "Are the APIs documented and tested, and are proper tools and standards employed to accomplish this?"
    options: "Poor", "Weak", "Moderate", "Good"

	affects SoftwareReliability on level Weak with values [0.0, 0.0, 0.5, 1.0]

	affects SoftwareMaintainability
		on level Moderate with values [0.0, 0.5, 1.0, 1]
		on level Good with values [0.0, 0.0, 0.0, 1]
}

question m3 {
    questionnaire: ApiAndIntegration
    title: "Are conventions and best practices (such as rules related to HTTP verbs in REST) considered in the API design?"
    options: "Poor", "Weak", "Moderate", "Good"

	affects SoftwareMaintainability
		on level Moderate with values [0.0, 0.5, 1.0, 1]
		on level Good with values [0.0, 0.0, 0.0, 1]
}

question m4 {
    questionnaire: ApiAndIntegration
    title: "Are the APIs secured using well-known standards such as JWT and Oauth2?"
    options: "No", "Yes"

	affects SoftwareMaintainability on level Moderate with values [0.0, 1.0]
}

question m5 {
    questionnaire: ApiAndIntegration
    title: "Are provisions made for supporting multiple versions of an API?"
    options: "No", "Yes"

	affects SoftwareMaintainability on level Good with values [0.0, 1.0]
}

question m6 {
    questionnaire: ApiAndIntegration
    title: "Is an API Gateway utilized for exposing services	?"
    options: "Never", "Rarely", "Frequently", "Always"

	affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m7 {
    questionnaire: ApiAndIntegration
    title: "Is the API designed to be independent of any specific technology?"
    options: "Poor", "Weak", "Moderate", "Good"

	affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m8 {
    questionnaire: ApiAndIntegration
    title: "Is the API designed with no dependence on the implementation approach?"
    options:"Poor", "Weak", "Moderate", "Good"

	affects SoftwareMaintainability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m9 {
    questionnaire: ApiAndIntegration
    title: "Are there comprehensive error codes for different scenarios?"
    options: "Poor", "Weak", "Moderate", "Good"

	affects SoftwareReliability
		on level Weak with values [0.0, 1.0, 1.0, 1.0]
		on level Moderate with values [0.0, 0.0, 1.0, 1.0]
		on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m10 {
    questionnaire: ApiAndIntegration
    title: "Are external services encapsulated to control the rate of change propagation?"
    options: "Never", "Rarely", "Frequently", "Always"


	affects SoftwareMaintainability
		on level Moderate with values [0.0, 0.5, 1.0, 1.0]
		on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m11 {
    questionnaire: ApiAndIntegration
    title: "Is system stability ensured by implementing timeouts and circuit breakers for external services?"
    options: "Never", "Rarely", "Frequently", "Always"

	affects SoftwareReliability
		on level Moderate with values [0.0, 0.5, 1.0, 1.0]
		on level Good with values [0.0, 0.0, 0.0, 1.0]

	affects SoftwareEfficiency
		on level Moderate with values [0.0, 0.5, 1.0, 1.0]
		on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m12 {
    questionnaire: ApiAndIntegration
    title: "Are asynchronous communication patterns employed when applicable?"
    options: "Never", "Rarely", "Frequently", "Always"

	affects SoftwareReliability
		on level Moderate with values [0.0, 0.5, 1.0, 1.0]
		on level Good with values [0.0, 0.0, 0.0, 1.0]


	affects SoftwareEfficiency
		on level Moderate with values [0.0, 0.5, 1.0, 1.0]
		on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m1 {
    questionnaire: CleanArchitecture
    title: "Does your software have architectural coherence and unified identity in general?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0, 0.0, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m2 {
    questionnaire: CleanArchitecture
    title: "Is your software divided into multiple services in a way that minimizes the interaction between different services?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0, 0.0, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m3 {
    questionnaire: CleanArchitecture
    title: "Have suitable architectural styles and patterns (such as microservices, service-oriented, message-based, actor model, event sourcing, etc.) been chosen for your software?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0, 0.0, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]

    affects SoftwareEfficiency
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0, 0.0, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects SoftwareReliability
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0, 0.0, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m4 {
    questionnaire: CleanArchitecture
    title: "Have the functionalities of the system been properly divided among the components?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0, 0.0, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]
}

question m5 {
    questionnaire: CleanArchitecture
    title: "Has the appropriate layering architecture (e.g. hexagonal, three-tier, DDD, etc.) been chosen for the system?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0, 0.0, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]
}

question m6 {
    questionnaire: CleanArchitecture
    title: "Is the system resilient to changes in the used technologies (e.g. libraries, databases, etc)?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0, 0.0, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]

    affects SoftwareEfficiency
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0, 0.0, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects SoftwareReliability
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0, 0.0, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m7 {
    questionnaire: CleanArchitecture
    title: "Is the system deployable on a cloud infrastructure?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m8 {
    questionnaire: CleanArchitecture
    title: "Is the development of modules based on the microservices or service-oriented architecture approach?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m9 {
    questionnaire: CleanArchitecture
    title: "Have Service Mesh or ESB been employed for system communications?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m10 {
    questionnaire: CleanArchitecture
    title: "Has a service discovery mechanism been used for component communication?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m11 {
    questionnaire: CleanArchitecture
    title: "Is the development of the user interface done with a micro front-end approach?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m12 {
    questionnaire: CleanArchitecture
    title: "Has the system made proper use of Aspect-Oriented Programming (AOP)?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects SoftwareReliability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects SoftwareEfficiency
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m13 {
    questionnaire: CleanArchitecture
    title: "Has the system made proper use of Reactive Programming?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m14 {
    questionnaire: CleanArchitecture
    title: "Is there the capability for customers to adjust variables related to business rules and processes as needed?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects SoftwareEfficiency
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m15 {
    questionnaire: CleanArchitecture
    title: "Has the software been designed in a way that a considerable amount of change requests can be accommodated without requiring any modifications to the software?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects SoftwareEfficiency
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m16 {
    questionnaire: CleanArchitecture
    title: "Is there an up-to-date software architecture document that offers a comprehensive and fitting overview of the overall architecture, and is it being effectively utilized and referenced as necessary?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects TeamPerformanceStability
    	on level Weak with values [0.0, 1.0, 1.0, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m17 {
    questionnaire: CleanArchitecture
    title: "Has the software architecture document adequately addressed 4+1 views or C4 model?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects TeamPerformanceStability
    	on level Weak with values [0.0, 1.0, 1.0, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m18 {
    questionnaire: CleanArchitecture
    title: "Are other architectural views such as data, logging, monitoring, testing, etc. also covered in the software architecture document as needed?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects TeamPerformanceStability
    	on level Weak with values [0.0, 1.0, 1.0, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m19 {
    questionnaire: CleanArchitecture
    title: "Are constraints and standards outlined in the software architecture document?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects TeamPerformanceStability
    	on level Weak with values [0.0, 1.0, 1.0, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m20 {
    questionnaire: CleanArchitecture
    title: "Is there a thorough list of non-functional requirements (quality attributes) along with a specification of their desired conditions approved by stakeholders, preferably in the software architecture document?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects TeamPerformanceStability
    	on level Weak with values [0.0, 1.0, 1.0, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m21 {
    questionnaire: CleanArchitecture
    title: "Are the techniques used to achieve the quality attributes documented?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects TeamPerformanceStability
    	on level Weak with values [0.0, 1.0, 1.0, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m22 {
    questionnaire: CleanArchitecture
    title: "Is there documentation for the architectural decision points, which includes the options and the reasoning behind their selection?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects TeamPerformanceStability
    	on level Weak with values [0.0, 1.0, 1.0, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m23 {
    questionnaire: CleanArchitecture
    title: "Are there clear and well-defined rules regarding the structure and relationships of classes and packages? Are these rules being followed?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects TeamPerformanceStability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}
question m1 {
    questionnaire: CodeLifecycle
    title: "Is a suitable code repository being used for managing and maintaining the code?"
    options: "No", "Yes"

    affects TeamAgileWorkflow on level Weak with values [0.5, 1.0]
}

question m2 {
    questionnaire: CodeLifecycle
    title: "Is a clear and suitable flow being used for working with the code repository, such as Git Flow, GitLab Flow, etc.?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]
}

question m3 {
    questionnaire: CodeLifecycle
    title: "Does new feature developments only take place on feature branches or hotfix branches?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects TeamAgileWorkflow on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m4 {
    questionnaire: CodeLifecycle
    title: "Does the team follow any specific naming conventions for branches and tags?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m5 {
    questionnaire: CodeLifecycle
    title: "Are staging branches distinct from stable branches or tags?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m6 {
    questionnaire: CodeLifecycle
    title: "Are there well-defined procedures for applying hotfixes to all supported versions?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m7 {
    questionnaire: CodeLifecycle
    title: "Are the main branches in the Git repository protected to limit access?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Weak with values [0.0, 0.0, 0.5, 1.0]
}

question m8 {
    questionnaire: CodeLifecycle
    title: "Is a merge request created and code reviewed prior to merging code into the main branches?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareMaintainability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects TeamAgileWorkflow on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]
}

question m9 {
    questionnaire: CodeLifecycle
    title: "Is there a clear and appropriate guideline for writing commit messages?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m10 {
    questionnaire: CodeLifecycle
    title: "Is it possible to link and track commits and code changes to project tasks?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m11 {
    questionnaire: CodeLifecycle
    title: "Are the code management and code analysis tools integrated?"
    options: "No", "Yes"

    affects SoftwareReliability on level Good with values [0.5, 1.0]

    affects SoftwareEfficiency on level Good with values [0.5, 1.0]

    affects SoftwareMaintainability on level Good with values [0.5, 1.0]

    affects TeamAgileWorkflow on level Good with values [0.5, 1.0]
}

question m12 {
    questionnaire: CodeLifecycle
    title: "Are the code management and task management tools integrated?"
    options: "No", "Yes"

    affects TeamAgileWorkflow on level Moderate with values [0.5, 1.0]
}

question m13 {
    questionnaire: CodeLifecycle
    title: "Is it possible to install and deploy feature code in the main branches independently of feature release using techniques such as feature flags and dark launches?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m1 {
    questionnaire: CodeQuality
    title: "Does the overall code structure enable easy identification of the architecture and capabilities of modules?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]
}

question m2 {
    questionnaire: CodeQuality
    title: "Is there a set of standards and conventions for code styling? Are they being followed?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareMaintainability
    	on level Weak with values [0.0, 1, 1.0, 1]
    	on level Moderate with values [0, 0, 0.5, 1.0]
}

question m3 {
    questionnaire: CodeQuality
    title: "Is the naming of classes, methods, and files suitable?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Weak with values [0.0, 1, 1.0, 1]
    	on level Moderate with values [0, 0, 0.5, 1.0]
}

question m4 {
    questionnaire: CodeQuality
    title: "Do the package dependencies conform to best practices? (with no circular dependencies or other issues)?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]
}

question m5 {
    questionnaire: CodeQuality
    title: "Are design patterns utilized in a proper and fitting manner?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]
}

question m6 {
    questionnaire: CodeQuality
    title: "Is a proper tool employed for analyzing code quality and identifying technical debt?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m7 {
    questionnaire: CodeQuality
    title: "Is method complexity being measured and are efforts being made to avoid creating complex methods?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m8 {
    questionnaire: CodeQuality
    title: "Is the code in a suitable state in terms of the absence of duplicate code?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m9 {
    questionnaire: CodeQuality
    title: "Is the size of classes and methods suitable?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m10 {
    questionnaire: CodeQuality
    title: "Is the code in a suitable state in terms of the absence of dead code?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m11 {
    questionnaire: CodeQuality
    title: "Is the codebase in a suitable state to handle small changes without causing a cascade of subsequent changes in many different areas?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]

    affects SoftwareReliability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]
}

question m12 {
    questionnaire: CodeQuality
    title: "Is the dependency injection framework utilized?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareMaintainability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m13 {
    questionnaire: CodeQuality
    title: "Is the codebase well-organized with clear interfaces and implementations?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]
}

question m14 {
    questionnaire: CodeQuality
    title: "Does the code follow the single responsibility principle (SRP) in terms of class and package design?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]
}

question m15 {
    questionnaire: CodeQuality
    title: "Are there appropriate comments in the code where necessary?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareMaintainability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m16 {
    questionnaire: CodeQuality
    title: "Is there a proper naming convention or standard in place for test classes and methods, and are they being followed?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m17 {
    questionnaire: CodeQuality
    title: "Is the test-first and TDD approach being used? And is the appropriate tool being used for this purpose?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects TeamAgileWorkflow on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m18 {
    questionnaire: CodeQuality
    title: "Do unit tests and integration tests have sufficient coverage (according to the testing pyramid)?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]

    affects SoftwareReliability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]
}

question m19 {
    questionnaire: CodeQuality
    title: "Are the automated tests running at an acceptable speed?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareEfficiency
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]

    affects SoftwareReliability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]
}

question m20 {
    questionnaire: CodeQuality
    title: "Have test doubles (such as mocks, fakes, etc.) been effectively utilized in the tests?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]

    affects SoftwareReliability
    	on level Moderate with values [0.0, 0.5, 1.0, 1]
    	on level Good with values [0, 0, 0.0, 1.0]
}

question m21 {
    questionnaire: CodeQuality
    title: "Has sufficient attention been paid to the quality of the test code? For example, have tasks such as refactoring, factorization, and using relevant design patterns (such as ObjectMother) been performed?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m22 {
    questionnaire: CodeQuality
    title: "Do the tests tolerate changes and refactoring in the main code well?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m23 {
    questionnaire: CodeQuality
    title: "Are suitable assertions utilized to recognize issues and failures in the operational environment?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects TeamAgileWorkflow on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m24 {
    questionnaire: CodeQuality
    title: "Are there any techniques  employed to revert the system to its prior state before running the tests?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m25 {
    questionnaire: CodeQuality
    title: "Is an appropriate technique being utilized to generate test data?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m1 {
    questionnaire: DevOps
    title: "Are automated build tools (e.g. Maven, Gradle, Ant) being  effectively utilized in development environment?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Weak with values [0.0, 0.0, 0.5, 1.0]
}

question m2 {
    questionnaire: DevOps
    title: "Are dependency management tools like Maven and NuGet being utilized?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareMaintainability on level Weak with values [0.0, 0.0, 0.5, 1.0]
}

question m3 {
    questionnaire: DevOps
    title: "Is there a suitable CI pipeline in place utilizing appropriate tools such as Jenkins and Gitlab-CI?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareMaintainability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m4 {
    questionnaire: DevOps
    title: "Is the maintenance of executable artifacts carried out using an appropriate asset management tool (such as Nexus and Artifactory)?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareEfficiency on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects TeamAgileWorkflow on level Weak with values [0.0, 0.0, 0.5, 1.0]
}

question m5 {
    questionnaire: DevOps
    title: "Are executable artifacts built in the CI pipeline?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m6 {
    questionnaire: DevOps
    title: "Are containers built automatically in the CI pipeline?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m7 {
    questionnaire: DevOps
    title: "Is there a clear and automated process for releasing versions?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m8 {
    questionnaire: DevOps
    title: "Are tags created automatically  prior to releasing versions and creating artifacts?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m9 {
    questionnaire: DevOps
    title: "Are the versioned built artifacts immutable (can not be modified)?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m10 {
    questionnaire: DevOps
    title: "Is it possible to run the built containers on cloud platforms such as Kubernetes?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m11 {
    questionnaire: DevOps
    title: "Is semantic versioning employed?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m12 {
    questionnaire: DevOps
    title: "Are release notes being generated for the released versions?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m13 {
    questionnaire: DevOps
    title: "Is the execution time of CI/CD pipelines reasonable?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m14 {
    questionnaire: DevOps
    title: "Is running functional tests part of the CI/CD pipeline?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m15 {
    questionnaire: DevOps
    title: "Is static code analysis part of the CI/CD pipeline?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects TeamAgileWorkflow on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m16 {
    questionnaire: DevOps
    title: "Is the code coverage percentage measured continuously for automated tests at different levels?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m1 {
    questionnaire: LogAndMonitoring
    title: "Is a suitable technology (e.g. Logback, etc.) being utilized for logging?"
    options: "No", "Yes"

    affects SoftwareReliability on level Weak with values [0.5, 1.0]
}

question m2 {
    questionnaire: LogAndMonitoring
    title: "Is it possible to configure logging before installation and at runtime?"
    options: "No", "Yes"

    affects SoftwareReliability on level Moderate with values [0.5, 1.0]
}

question m3 {
    questionnaire: LogAndMonitoring
    title: "Is it possible to generate logs in different formats such as plain text, JSON, etc.?"
    options: "No", "Yes"

    affects SoftwareReliability on level Moderate with values [0.5, 1.0]
}

question m4 {
    questionnaire: LogAndMonitoring
    title: "Does the system support sending logs to various media (such as file, syslog, Kafka, etc.)?"
    options: "No", "Yes"

    affects SoftwareReliability on level Good with values [0.5, 1.0]
}

question m5 {
    questionnaire: LogAndMonitoring
    title: "Are important events such as Authentication and Authorization, Malware Activity, and Failure and Critical Errors being logged?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m6 {
    questionnaire: LogAndMonitoring
    title: "Is there logging for various kinds of events?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m7 {
    questionnaire: LogAndMonitoring
    title: "Are suitable logs being generated to identify errors in the operational environment?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m8 {
    questionnaire: LogAndMonitoring
    title: "Are suitable logs being generated for detecting system bottlenecks?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m9 {
    questionnaire: LogAndMonitoring
    title: "Are logs generated for analyzing user behavior?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareUsability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects TeamAgileWorkflow on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m10 {
    questionnaire: LogAndMonitoring
    title: "Is distributed log tracing (such as Zipkin) supported?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects TeamAgileWorkflow on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m11 {
    questionnaire: LogAndMonitoring
    title: "Is an appropriate tool (such as Sentry) used for error tracking?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m12 {
    questionnaire: LogAndMonitoring
    title: "Are health check services provided by processes for use in monitoring tools?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m13 {
    questionnaire: LogAndMonitoring
    title: "Is the internal state of processes monitored through appropriate technology (such as Spring Actuator) using metrics and sensors via metrics and sensors?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m14 {
    questionnaire: LogAndMonitoring
    title: "Are metric values exportable in appropriate formats (e.g. JSON and Prometheus)?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m15 {
    questionnaire: LogAndMonitoring
    title: "Is there a sufficient number of metrics available to track the health of various system components (e.g. database connections, query response time, threads, memory, GC, etc.)?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m16 {
    questionnaire: LogAndMonitoring
    title: "Is there a sufficient number of metrics available to detect system bottlenecks (such as response time to various requests in buckets)?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m17 {
    questionnaire: LogAndMonitoring
    title: "Can Memory Dump and Thread Dump be easily generated for optimization or troubleshooting purposes?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m1 {
    questionnaire: Methodology
    title: "Are appropriate methodologies, especially agile methodologies, being used in software development?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m2 {
    questionnaire: Methodology
    title: "Are appropriate tools (such as task management and boards) being emplo   d for managing the development methodology?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m3 {
    questionnaire: Methodology
    title: "Is the development methodology being properly implemented and its  elements effectively utilized, such as producing key artifacts (like a backlog)?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m4 {
    questionnaire: Methodology
    title: "Are methodology ceremonies (such as sprint meetings and stand-ups in Scrum) held regularly and efficiently?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m5 {
    questionnaire: Methodology
    title: "Are the team topologies appropriate and with clear understanding of their roles and responsibilities?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects TeamPerformanceStability
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m6 {
    questionnaire: Methodology
    title: "Is there a clear understanding of the roles and responsibilities of each team member?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects TeamPerformanceStability
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m7 {
    questionnaire: Methodology
    title: "Are tasks named, categorized, and described accurately in task management tools?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects TeamPerformanceStability
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m8 {
    questionnaire: Methodology
    title: "Does a proper mechanism exist for task estimation and planning?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m9 {
    questionnaire: Methodology
    title: "Is there and effective feedback loop and a suitable mechanism for improving team processes (such as Retrospective meeting)?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m10 {
    questionnaire: Methodology
    title: "Is the process of receiving and tracking feedback from customers/stakeholders systematic, fast, and simple??"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m11 {
    questionnaire: Methodology
    title: "Are project risks being monitored, analyzed, and managed according to a documented plan?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects TeamAgileWorkflow on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m12 {
    questionnaire: Methodology
    title: "Are technical debts being tracked and resolved based on a documented plan?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareMaintainability
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m13 {
    questionnaire: Methodology
    title: "Are team tools data sources such as Git and Jira regularly backed up?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]
}

question m14 {
    questionnaire: Methodology
    title: "Is there a defined plan for software release?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects TeamAgileWorkflow on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m15 {
    questionnaire: Methodology
    title: "Is the BDD approach being utilized in software development?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects TeamAgileWorkflow on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m1 {
    questionnaire: QualityConsequences
    title: "Is the service availability rate acceptable?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability
		on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m2 {
    questionnaire: QualityConsequences
    title: "Is the system's response time to client requests reasonable?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency
		on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m3 {
    questionnaire: QualityConsequences
    title: "Is the rate of reporting new bugs low?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability
		on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects SoftwareMaintainability
		on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m4 {
    questionnaire: QualityConsequences
    title: "Is the application easy to learn and use for users?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareUsability
		on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m5 {
    questionnaire: QualityConsequences
    title: "Is the required time and cost for developing new features reasonable?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareMaintainability
		on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m6 {
    questionnaire: QualityConsequences
    title: "Are security vulnerabilities and reported bugs being addressed within a reasonable time?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability
		on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects SoftwareMaintainability
		on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m7 {
    questionnaire: QualityConsequences
    title: "Is the change failure rate acceptable?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability
		on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects SoftwareMaintainability
		on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects TeamAgileWorkflow
		on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m8 {
    questionnaire: QualityConsequences
    title: "Is the rate of recurrence of old bugs due to new changes low?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability
		on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m1 {
    questionnaire: SoftwareQualityTest
    title: "Is an adequate tool and approach used to perform load, stress, and performance testing?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects SoftwareEfficiency
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m2 {
    questionnaire: SoftwareQualityTest
    title: "Is the coverage of load, stress, and performance testing sufficient?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]

    affects SoftwareEfficiency
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m3 {
    questionnaire: SoftwareQualityTest
    title: "Are responses being delivered to clients in a timely and accurate manner across various circumstances?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m4 {
    questionnaire: SoftwareQualityTest
    title: "Have various scenarios of outages and disruption of services been tested to ensure the effectiveness of availability preservation techniques?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m5 {
    questionnaire: SoftwareQualityTest
    title: "Is an adequate tool and approach considered for end-to-end testing?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m6 {
    questionnaire: SoftwareQualityTest
    title: "Is there sufficient coverage of critical and sensitive scenarios through end-to-end tests?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareMaintainability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m7 {
    questionnaire: SoftwareQualityTest
    title: "Have soak testing and smoke testing been employed to ensure the stability of the system?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m8 {
    questionnaire: SoftwareQualityTest
    title: "Is the appropriate tool (such as TestLink) employed for test management?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]
}

question m9 {
    questionnaire: SoftwareQualityTest
    title: "Is there a comprehensive documentation for manual acceptance testing?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareReliability
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m10 {
    questionnaire: SoftwareQualityTest
    title: "Is there a clear procedure for acceptance testing after each installation?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m11 {
    questionnaire: SoftwareQualityTest
    title: "Is there a testing environment (staging) that is similar to the production environment in terms of data and access rights?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}
question m1 {
    questionnaire: SoftwareQualityTunning
    title: "Is it feasible for the system to keep operating if a problem occurs only in one part of the system?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m2 {
    questionnaire: SoftwareQualityTunning
    title: "Is redundancy supported in different architectural components to avoid a single point of failure?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability
    	on level Weak with values [0.0, 1.0, 1, 1]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m3 {
    questionnaire: SoftwareQualityTunning
    title: "Is there a proper mechanism for analyzing and tuning the performance of the database?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m4 {
    questionnaire: SoftwareQualityTunning
    title: "Is there a suitable mechanism for analyzing data layer performance and optimizing queries?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m5 {
    questionnaire: SoftwareQualityTunning
    title: "Is parallel and distributed processing possible?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m6 {
    questionnaire: SoftwareQualityTunning
    title: "Is the use of a load balancer supported in the architecture?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Weak with values [0.0, 0.0, 0.5, 1.0]
}

question m7 {
    questionnaire: SoftwareQualityTunning
    title: "Are non-blocking IO techniques implemented?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m8 {
    questionnaire: SoftwareQualityTunning
    title: "Is resource pooling utilized when it is beneficial? (e.g., database connection pooling, thread pooling, etc.)?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m9 {
    questionnaire: SoftwareQualityTunning
    title: "Are timeouts set for accessing various resources and services?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m10 {
    questionnaire: SoftwareQualityTunning
    title: "Are rate limiting and throttling taken into account?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m11 {
    questionnaire: SoftwareQualityTunning
    title: "Are caching and CDN employed?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m12 {
    questionnaire: SoftwareQualityTunning
    title: "Are processes designed to be as stateless as possible?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m13 {
    questionnaire: SoftwareQualityTunning
    title: "Has an appropriate approach been utilized for storing the application state?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m14 {
    questionnaire: SoftwareQualityTunning
    title: "Is an appropriate tool or approach used for profiling the application?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m1 {
    questionnaire: TeamLearning
    title: "Is an appropriate tool being used to manage knowledge and documentation?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamAgileWorkflow on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]
}

question m2 {
    questionnaire: TeamLearning
    title: "Is the volume and growth rate of documents in the knowledge management tool appropriate?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m3 {
    questionnaire: TeamLearning
    title: "Are the documents in the knowledge management system well-organized?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m4 {
    questionnaire: TeamLearning
    title: "Are the documents thorough and easy to understand?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m5 {
    questionnaire: TeamLearning
    title: "Are there templates, checklists, and guides available for for different types of required documents?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m6 {
    questionnaire: TeamLearning
    title: "Is there a similar process to code review for reviewing the quality of documents?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m7 {
    questionnaire: TeamLearning
    title: "Is there a requirement for the team members to document and share their knowledge? For instance, could capturing knowledge be an essential component of the \"definition of done\" for any task?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m8 {
    questionnaire: TeamLearning
    title: "Are the results of the conducted researches being documented?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects TeamPerformanceStability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m9 {
    questionnaire: TeamLearning
    title: "Are important documents such as architecture, quality assurance, and workflow documents published and updated  along with clear identification of their objectives and stakeholders?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m10 {
    questionnaire: TeamLearning
    title: "Are frequent problems, errors, and mistakes documented and shared with their respective solutions?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects TeamAgileWorkflow on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m11 {
    questionnaire: TeamLearning
    title: "Are there documented learning resources available, particularly for new team members?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m12 {
    questionnaire: TeamLearning
    title: "Does the team have any improvement programs, such as learning courses, conferences, discussion sessions, etc.?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m13 {
    questionnaire: TeamLearning
    title: "Does a blameless postmortem analysis take place when major incidents occur?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects TeamAgileWorkflow on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m1 {
    questionnaire: Technology
    title: "Have the latest versions of technologies been utilized?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareMaintainability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]
}

question m2 {
    questionnaire: Technology
    title: "Have reliable and secure technologies been used?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareMaintainability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m3 {
    questionnaire: Technology
    title: "Have the latest versions of technologies been utilized??"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m4 {
    questionnaire: Technology
    title: "Has care been taken to use technologies with official support still available?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m5 {
    questionnaire: Technology
    title: "Have efforts been made to avoid reproducing existing and applicable technologies,especially open-source alternatives?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareMaintainability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Weak with values [0.0, 0.0, 0.5, 1.0]
}

question m6 {
    questionnaire: Technology
    title: "Is there a regular procedure for checking and updating technologies?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m7 {
    questionnaire: Technology
    title: "Is the ease of upgrading technology versions considered in selecting them?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareMaintainability on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects TeamPerformanceStability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m8 {
    questionnaire: Technology
    title: "Has technology selection been made consciously and through a systematic comparison (documenting criteria and various options)?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m9 {
    questionnaire: Technology
    title: "Have trends been considered in selecting technologies?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m10 {
    questionnaire: Technology
    title: "Are reliable benchmarks and reports taken into account when selecting technologies?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m11 {
    questionnaire: Technology
    title: "Are custom benchmarks conducted for selecting technologies in sensitive cases?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Good with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m12 {
    questionnaire: Technology
    title: "Is the availability of skilled human resources considered in the selection of technologies?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects TeamPerformanceStability on level Weak with values [0.0, 0.0, 0.5, 1.0]
}

question m13 {
    questionnaire: Technology
    title: "Is the availability of suitable support services considered when selecting technologies, particularly in sanction circumstances?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareReliability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m14 {
    questionnaire: Technology
    title: "Is the expense of technology licenses proportional to the project's requirements?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareEfficiency on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m1 {
    questionnaire: UiUx
    title: "Have the pages been designed to be responsive to different screen sizes and device types?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareMaintainability on level Moderate with values [0.0, 0.0, 0.5, 1.0]

    affects SoftwareUsability on level Moderate with values [0.0, 0.0, 0.5, 1.0]
}

question m2 {
    questionnaire: UiUx
    title: "Is multilingualism and the ease of adding new languages supported?"
    options: "No", "Yes"

    affects SoftwareUsability on level Good with values [0.0, 1.0]
}

question m3 {
    questionnaire: UiUx
    title: "Is the app theme and appearance modern and user-friendly?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareUsability
    	on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m4 {
    questionnaire: UiUx
    title: "Are the forms designed to be easily understandable and user-friendly?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareUsability
    	on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m5 {
    questionnaire: UiUx
    title: "Is the layout of the forms suitable?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects SoftwareUsability
    	on level Weak with values [0.0, 1.0, 1.0, 1.0]
    	on level Moderate with values [0.0, 0.0, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m6 {
    questionnaire: UiUx
    title: "Are form validations executed before sending them to the server and is the required guidance provided to the user?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareUsability
    	on level Moderate with values [0.0, 0.5, 1.0, 1.0]
    	on level Good with values [0.0, 0.0, 0.0, 1.0]
}

question m7 {
    questionnaire: UiUx
    title: "Can the interface color scheme and size be personalized by the user?"
    options: "No", "Yes"

    affects SoftwareUsability on level Good with values [0.0, 1.0]
}

question m8 {
    questionnaire: UiUx
    title: "Is it possible to personalize widgets such as rearranging table columns order and etc?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareUsability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m9 {
    questionnaire: UiUx
    title: "Is it possible to customize widget layout and create a custom dashboard?"
    options: "No", "Yes"

    affects SoftwareUsability on level Good with values [0.0, 1.0]
}

question m10 {
    questionnaire: UiUx
    title: "Are there features in the user interface to assist people with disabilities	?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects SoftwareUsability on level Good with values [0.0, 0.0, 0.5, 1.0]
}

question m11 {
    questionnaire: UiUx
    title: "Can users give immediate feedback in case of errors?"
    options: "No", "Yes"

    affects SoftwareUsability on level Moderate with values [0.0, 1.0]
}

question m12 {
    questionnaire: UiUx
    title: "Are unknown errors encoded in logs for rapid tracking?"
    options: "No", "Yes"

    affects SoftwareUsability on level Moderate with values [0.0, 1.0]
}

subject Software {
	title: "Software"
	description: ""
}

subject Team {
	title: "Team"
	description: ""
}
