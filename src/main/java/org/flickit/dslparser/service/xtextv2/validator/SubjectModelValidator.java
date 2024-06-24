package org.flickit.dslparser.service.xtextv2.validator;

import lombok.RequiredArgsConstructor;
import org.flickit.dsl.editor.v2.assessmentKitDsl.BaseInfo;
import org.flickit.dslparser.controller.exception.api.SyntaxError;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectModelValidator implements ModelValidator {

    @Override
    public List<SyntaxError> validate(Context context) {
        return validateSubjectsHaveAttribute(context);
    }

    private List<SyntaxError> validateSubjectsHaveAttribute(Context context) {
        Set<String> codes = context.subjectModels().getModels().stream()
                .map(BaseInfo::getName)
                .collect(Collectors.toSet());
        Set<String> subjectCodesOnAttribute = context.attributeModels().getModels().stream()
                .map(a -> a.getSubject().getName())
                .collect(Collectors.toSet());

        codes.removeAll(subjectCodesOnAttribute);
        return codes.stream()
                .map(c -> new SyntaxError(String.format("Subject with [%s] id should have at least one Attribute", c)))
                .toList();
    }
}
