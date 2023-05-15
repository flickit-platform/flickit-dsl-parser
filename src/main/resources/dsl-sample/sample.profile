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


Questionnaire clean_architecture {
	title: "Clean Architecture"
	description: ""
}

Questionnaire code_quality {
	title: "Code Quality"
	description: ""
}

Questionnaire software_quality_test {
	title: "Software Quality Test"
	description: ""
}

Questionnaire software_quality_tunning {
	title: "Software Quality Tunning"
	description: ""
}

Questionnaire dev_ops {
	title: "DevOps"
	description: ""
}

Questionnaire code_lifecycle {
	title: "Code Life Cycle"
	description: ""
}

Questionnaire technology {
	title: "Technology"
	description: ""
}

Questionnaire ui_ux {
	title: "UI/UX"
	description: ""
}

Questionnaire api_and_integration {
	title: "API and Integration"
	description: ""
}

Questionnaire methodology {
	title: "Methodology"
	description: ""
}

Questionnaire team_learning {
	title: "Team Learning"
	description: ""
}


Questionnaire log_and_monitoring {
	title: "Log and Monitoring"
	description: ""
}

Questionnaire quality_consequences {
	title: "Quality Consequences"
	description: ""
}

Subject software {
	title: "Software"
	description: ""
	questionnaires: clean_architecture, code_quality, software_quality_test, software_quality_tunning, dev_ops, code_lifecycle, technology, ui_ux, api_and_integration, methodology, log_and_monitoring, quality_consequences
}

Subject team {
	title: "Team"
	description: ""
	questionnaires: clean_architecture, code_quality, software_quality_test, dev_ops, code_lifecycle, technology, methodology, team_learning, log_and_monitoring, quality_consequences
}

QualityAttribute software_reliability {
    title: "Software-Reliability"
    description: ""
    subject: software
}

QualityAttribute software_efficiency {
    title: "Software-Efficiency"
    description: ""
    subject: software
}

QualityAttribute software_maintainability  {
    title: "Software-Maintainability "
    description: ""
    subject: software
}

QualityAttribute software_usability  {
    title: "Software-Usability "
    description: ""
    subject: software
}

QualityAttribute team_agile_workflow {
    title: "Team-Agile Workflow"
    description: ""
    subject: team
}

QualityAttribute team_performance_stability {
    title: "Team-Performance Stability"
    description: ""
    subject: team
}