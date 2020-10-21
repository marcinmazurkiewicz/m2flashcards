package dev.mazurkiewicz.m2flashcards.tag;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagMapper {

    public Tag mapRequestToEntity(TagRequest request) {
        Tag result = new Tag();
        result.setId(request.getId());
        result.setTagName(request.getTagName());
        return result;

    }

    public List<Tag> mapRequestListToEntityList(List<TagRequest> tagRequests) {
        return tagRequests.stream()
                .map(this::mapRequestToEntity)
                .collect(Collectors.toList());

    }

    public TagResponse mapEntityToResponse(Tag tag) {
        TagResponse result = new TagResponse();
        result.setId(tag.getId());
        result.setTagName(tag.getTagName());
        return result;
    }

    public List<TagResponse> mapEntityListToResponseList(List<Tag> tags) {
        return tags.stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toList());
    }
}
