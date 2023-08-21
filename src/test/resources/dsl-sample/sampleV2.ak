level Elementary {
    title: "Elementary"
    index: 1
}

level Weak {
    title:"Weak"
    index: 2
    competence: [Weak:60%]
}

level Moderate {
    title:"Moderate"
    index: 3
    competence: [Weak:75%, Moderate: 60%]
}

subject team {
    title: "Team"
    description: "This is a test Description for Team Subject"
    index: 1
    weight: 1
}

subject software {
    title: "Software"
    index: 2
    weight: 2
}

questionnaire DevOps {
    title: "DEV OPS"
    description: "This is a test Description for DEV OPS category"
    index: 1
}

questionnaire Security {
    title: "Security"
    description: "This is a test Description for Code Security category"
    index: 2
}

attribute TeamReflection {
    title: "Team Reflection"
    description: "This is a test Description for Team Reflection"
    index: 2
    subject: team
    weight: 3
}

attribute TeamSecurity {
    title: "Team-Security"
    index: 2
    subject: team
}

question q1 {
    questionnaire: DevOps
    guide: "Answer to the question of this metric has 3 options. This metric has impact on one attribute."

    title: "metric question test m1?"
    options: "Cap1", "Cap2", "Cap3"

    affects TeamReflection on level Moderate
 }

question q2 {
    questionnaire: DevOps
    guide: "This metric has impact on 3 attributes."

    title: "metric question test m2?"
    options: "Cap1", "Cap2", "Cap3"

    affects TeamReflection on level Moderate with values [0, 1, 1] with weight 2
    affects TeamSecurity on level Elementary

    mayNotBeApplicable: true
 }

question q3 {
    questionnaire: DevOps
    title: "metric question test m1?"
    options: "cap1", "cap2", "cap3", "cap4", "cap5"

    affects  TeamReflection
        on level Weak with values [0, 1, 1, 1, 1]
        on level Moderate with values [0, 0, 0.5, 1, 1] with weight 2
        on level Elementary with values [0, 0, 0, 0.5, 1]  with weight 3
}
