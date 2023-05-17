Level RISKY {
    title: "Elementary"
}
Level WEAK {
    title:"Weak"
    competence: [WEAK:60%]
}
Level NORMAL {
    title:"Moderate"
    competence: [WEAK:75%, NORMAL: 60%]
 }
Level GOOD {
    title:"Good"
    competence: [WEAK:85%, NORMAL: 75%, GOOD:60%]
}
Level GREAT {
    title:"Great"
    competence: [WEAK:95%, NORMAL: 85%, GOOD:75%, GREAT: 60%]
}

Metric m2 {
    questionnaire: ui_ux
    question: "Is multilingualism and the ease of adding new languages supported?"
    options: "No", "Yes"

    affects WEAK level of software_usability with values {0.0, 1.0} weight 2
    affects WEAK level of software_efficiency with values {0.0, 1.0}
}

Metric m3 {
    questionnaire: ui_ux
    question: "Is the app theme and appearance modern and user-friendly?"
    options: "Poor", "Weak", "Moderate", "Good"

    options 1 to 2 affects on RISKY level of software_usability with values {0.0, 1.0} weight 2
    options 2 to 3 affects on RISKY level of software_efficiency with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of software_usability with values {0.0, 1.0}
}


Metric m1 {
    questionnaire: api_and_integration
    question: "Is API provided using standard and well-known communication protocols (such as REST and GraphQL)?"
    options: "No", "Yes"

    affects WEAK level of software_maintainability with values {0.0,1.0}
}
Metric m2 {
    questionnaire: api_and_integration
    question: "Are the APIs documented and tested, and are proper tools and standards employed to accomplish this?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects WEAK level of software_reliability with values {0.0, 0.0, 0.5, 1.0}

    options 1 to 3 affects on RISKY level of software_maintainability with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_maintainability with values {0.0, 1.0}
}
Metric m3 {
    questionnaire: api_and_integration
    question: "Are conventions and best practices (such as rules related to HTTP verbs in REST) considered in the API design?"
    options: "Poor", "Weak", "Moderate", "Good"

    options 1 to 3 affects on RISKY level of software_maintainability with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_maintainability with values {0.0, 1.0}
}
Metric m4 {
    questionnaire: api_and_integration
    question: "Are the APIs secured using well-known standards such as JWT and Oauth2?"
    options: "No", "Yes"

    affects RISKY level of software_maintainability with values {0.0, 1.0}
}
Metric m5 {
    questionnaire: api_and_integration
    question: "Are provisions made for supporting multiple versions of an API?"
    options: "No", "Yes"

    affects NORMAL level of software_maintainability with values {0.0, 1.0}
}
Metric m6 {
    questionnaire: api_and_integration
    question: "Is an API Gateway utilized for exposing services	?"
    options: "Never", "Rarely", "Frequently", "Always"

    affects NORMAL level of software_reliability with values {0.0, 0.0, 0.5, 1.0}
}
Metric m7 {
    questionnaire: api_and_integration
    question: "Is the API designed to be independent of any specific technology?"
    options: "Poor", "Weak", "Moderate", "Good"

    affects NORMAL level of software_maintainability with values {0.0, 0.0, 0.5, 1.0}
}
Metric m8 {
    questionnaire: api_and_integration
    question: "Is the API designed with no dependence on the implementation approach?"
    options:"Poor", "Weak", "Moderate", "Good"

    affects RISKY level of software_maintainability with values {0.0, 0.0, 0.5, 1.0}
}
Metric m9 {
    questionnaire: api_and_integration
    question: "Are there comprehensive error codes for different scenarios?"
    options: "Poor", "Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of software_reliability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of software_reliability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of software_reliability with values {0.0, 1.0}
}
Metric m10 {
    questionnaire: api_and_integration
    question: "Are external services encapsulated to control the rate of change propagation?"
    options: "Never", "Rarely", "Frequently", "Always"

    options 1 to 3 affects on RISKY level of software_maintainability with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_maintainability with values {0.0, 1.0}
}
Metric m11 {
    questionnaire: api_and_integration
    question: "Is system stability ensured by implementing timeouts and circuit breakers for external services?"
    options: "Never", "Rarely", "Frequently", "Always"

    options 1 to 3 affects on RISKY level of software_reliability with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_reliability with values {0.0, 1.0}

    options 1 to 3 affects on RISKY level of software_efficiency with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_efficiency with values {0.0, 1.0}
}
Metric m12 {
    questionnaire: api_and_integration
    question: "Are asynchronous communication patterns employed when applicable?"
    options: "Never", "Rarely", "Frequently", "Always"

    options 1 to 3 affects on RISKY level of software_reliability with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_reliability with values {0.0, 1.0}

    options 1 to 3 affects on RISKY level of software_efficiency with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_efficiency with values {0.0, 1.0}
}


Metric m1 {
    questionnaire: clean_architecture
    question: "Does your software have architectural coherence and unified identity in general?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of software_maintainability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of software_maintainability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of software_maintainability with values {0.0, 1.0}
}

Metric m2 {
    questionnaire: clean_architecture
    question: "Is your software divided into multiple services in a way that minimizes the interaction between different services?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of software_maintainability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of software_maintainability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of software_maintainability with values {0.0, 1.0}
}

Metric m3 {
    questionnaire: clean_architecture
    question: "Have suitable architectural styles and patterns (such as microservices, service-oriented, message-based, actor model, event sourcing, etc.) been chosen for your software?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of software_maintainability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of software_maintainability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of software_maintainability with values {0.0, 1.0}

    options 1 to 2 affects on WEAK level of software_efficiency with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of software_efficiency with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of software_efficiency with values {0.0, 1.0}

    options 1 to 2 affects on WEAK level of software_reliability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of software_reliability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of software_reliability with values {0.0, 1.0}
}

Metric m4 {
    questionnaire: clean_architecture
    question: "Have the functionalities of the system been properly divided among the components?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of software_maintainability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of software_maintainability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of software_maintainability with values {0.0, 1.0}
}

Metric m5 {
    questionnaire: clean_architecture
    question: "Has the appropriate layering architecture (e.g. hexagonal, three-tier, DDD, etc.) been chosen for the system?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of software_maintainability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of software_maintainability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of software_maintainability with values {0.0, 1.0}
}

Metric m6 {
    questionnaire: clean_architecture
    question: "Is the system resilient to changes in the used technologies (e.g. libraries, databases, etc)?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of software_maintainability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of software_maintainability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of software_maintainability with values {0.0, 1.0}

    options 1 to 2 affects on WEAK level of software_efficiency with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of software_efficiency with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of software_efficiency with values {0.0, 1.0}

    options 1 to 2 affects on WEAK level of software_reliability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of software_reliability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of software_reliability with values {0.0, 1.0}
}

Metric m7 {
    questionnaire: clean_architecture
    question: "Is the system deployable on a cloud infrastructure?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects NORMAL level of software_reliability with values {0.0, 0.0, 0.5, 1.0}

    affects NORMAL level of software_efficiency with values {0.0, 0.0, 0.5, 1.0}
}

Metric m8 {
    questionnaire: clean_architecture
    question: "Is the development of modules based on the microservices or service-oriented architecture approach?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects NORMAL level of software_maintainability with values {0.0, 0.0, 0.5, 1.0}
}

Metric m9 {
    questionnaire: clean_architecture
    question: "Have Service Mesh or ESB been employed for system communications?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects NORMAL level of software_maintainability with values {0.0, 0.0, 0.5, 1.0}

    affects NORMAL level of software_reliability with values {0.0, 0.0, 0.5, 1.0}

    affects NORMAL level of software_efficiency with values {0.0, 0.0, 0.5, 1.0}
}

Metric m10 {
    questionnaire: clean_architecture
    question: "Has a service discovery mechanism been used for component communication?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects NORMAL level of software_reliability with values {0.0, 0.0, 0.5, 1.0}
}

Metric m11 {
    questionnaire: clean_architecture
    question: "Is the development of the user interface done with a micro front-end approach?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects NORMAL level of software_maintainability with values {0.0, 0.0, 0.5, 1.0}
}

Metric m12 {
    questionnaire: clean_architecture
    question: "Has the system made proper use of Aspect-Oriented Programming (AOP)?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 3 affects on RISKY level of software_maintainability with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_maintainability with values {0.0, 1.0}

    options 1 to 3 affects on RISKY level of software_reliability with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_reliability with values {0.0, 1.0}

    options 1 to 3 affects on RISKY level of software_efficiency with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_efficiency with values {0.0, 1.0}
}

Metric m13 {
    questionnaire: clean_architecture
    question: "Has the system made proper use of Reactive Programming?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    affects NORMAL level of software_efficiency with values {0.0, 0.0, 0.5, 1.0}
}

Metric m14 {
    questionnaire: clean_architecture
    question: "Is there the capability for customers to adjust variables related to business rules and processes as needed?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 3 affects on RISKY level of software_maintainability with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_maintainability with values {0.0, 1.0}

    options 1 to 3 affects on RISKY level of software_efficiency with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_efficiency with values {0.0, 1.0}
}

Metric m15 {
    questionnaire: clean_architecture
    question: "Has the software been designed in a way that a considerable amount of change requests can be accommodated without requiring any modifications to the software?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 3 affects on RISKY level of software_maintainability with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_maintainability with values {0.0, 1.0}

    options 1 to 3 affects on RISKY level of software_efficiency with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_efficiency with values {0.0, 1.0}
}

Metric m16 {
    questionnaire: clean_architecture
    question: "Is there an up-to-date software architecture document that offers a comprehensive and fitting overview of the overall architecture, and is it being effectively utilized and referenced as necessary?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of team_performance_stability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of team_performance_stability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of team_performance_stability with values {0.0, 1.0}
}

Metric m17 {
    questionnaire: clean_architecture
    question: "Has the software architecture document adequately addressed 4+1 views or C4 model?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of team_performance_stability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of team_performance_stability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of team_performance_stability with values {0.0, 1.0}
}

Metric m18 {
    questionnaire: clean_architecture
    question: "Are other architectural views such as data, logging, monitoring, testing, etc. also covered in the software architecture document as needed?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of team_performance_stability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of team_performance_stability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of team_performance_stability with values {0.0, 1.0}
}

Metric m19 {
    questionnaire: clean_architecture
    question: "Are constraints and standards outlined in the software architecture document?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of team_performance_stability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of team_performance_stability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of team_performance_stability with values {0.0, 1.0}
}

Metric m20 {
    questionnaire: clean_architecture
    question: "Is there a thorough list of non-functional requirements (quality attributes) along with a specification of their desired conditions approved by stakeholders, preferably in the software architecture document?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of team_performance_stability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of team_performance_stability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of team_performance_stability with values {0.0, 1.0}
}

Metric m21 {
    questionnaire: clean_architecture
    question: "Are the techniques used to achieve the quality attributes documented?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of team_performance_stability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of team_performance_stability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of team_performance_stability with values {0.0, 1.0}
}

Metric m22 {
    questionnaire: clean_architecture
    question: "Is there documentation for the architectural decision points, which includes the options and the reasoning behind their selection?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 2 affects on WEAK level of team_performance_stability with values {0.0, 1.0}
    options 2 to 3 affects on RISKY level of team_performance_stability with values {0.0, 1.0}
    options 3 to 4 affects on NORMAL level of team_performance_stability with values {0.0, 1.0}
}

Metric m23 {
    questionnaire: clean_architecture
    question: "Are there clear and well-defined rules regarding the structure and relationships of classes and packages? Are these rules being followed?"
    options: "Poor" ,"Weak", "Moderate", "Good"

    options 1 to 3 affects on RISKY level of software_maintainability with values {0.0, 0.5, 1.0}
    options 3 to 4 affects on NORMAL level of software_maintainability with values {0.0, 1.0}
}



Questionnaire clean_architecture {
	title: "Clean Architecture"
	description: "tttttttt"
}

Questionnaire code_quality {
	title: "Code Quality"
	description: "ddddddddddd"
}

Questionnaire software_quality_test {
	title: "Software Quality Test"
	description: "rrrrrrrrrrrrrr"
}

Questionnaire software_quality_tunning {
	title: "Software Quality Tunning"
	description: "hhhhhhhh"
}

Questionnaire dev_ops {
	title: "DevOps"
	description: "ssssssssaaaaaa"
}

Questionnaire code_lifecycle {
	title: "Code Life Cycle"
	description: "cccccccc"
}

Questionnaire technology {
	title: "Technology"
	description: "xxxxxxxxxxxx"
}

Questionnaire ui_ux {
	title: "UI/UX"
	description: "llllllllll"
}

Questionnaire api_and_integration {
	title: "API and Integration"
	description: "uuuuuuuu"
}

Questionnaire methodology {
	title: "Methodology"
	description: "wwwwwwww"
}

Questionnaire team_learning {
	title: "Team Learning"
	description: "aaaaaaaaa"
}


Questionnaire log_and_monitoring {
	title: "Log and Monitoring"
	description: "xxxxxx"
}

Questionnaire quality_consequences {
	title: "Quality Consequences"
	description: "zzzzzzzzzz"
}

Subject software {
	title: "Software"
	description: "gfgfgfgf"
	questionnaires: clean_architecture, code_quality, software_quality_test, software_quality_tunning, dev_ops, code_lifecycle, technology, ui_ux, api_and_integration, methodology, log_and_monitoring, quality_consequences
}

Subject team {
	title: "Team"
	description: "ghghghghg"
	questionnaires: clean_architecture, code_quality, software_quality_test, dev_ops, code_lifecycle, technology, methodology, team_learning, log_and_monitoring, quality_consequences
}

QualityAttribute software_reliability {
    title: "Software-Reliability"
    description: "jkjkjkj"
    subject: software
}

QualityAttribute software_efficiency {
    title: "Software-Efficiency"
    description: "yuiyiyji"
    subject: software
}

QualityAttribute software_maintainability  {
    title: "Software-Maintainability "
    description: "fgbhdf"
    subject: software
}

QualityAttribute software_usability  {
    title: "Software-Usability "
    description: "dfbgsdfbg"
    subject: software
}

QualityAttribute team_agile_workflow {
    title: "Team-Agile Workflow"
    description: "dsfgbdf"
    subject: team
}

QualityAttribute team_performance_stability {
    title: "Team-Performance Stability"
    description: "sdfgbfthb"
    subject: team
    weight:3
}