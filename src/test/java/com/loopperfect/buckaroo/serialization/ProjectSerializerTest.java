package com.loopperfect.buckaroo.serialization;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.loopperfect.buckaroo.*;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class ProjectSerializerTest {

    @org.junit.Test
    public void testProjectSerializer1() throws Exception {

        final Project project = Project.of(
                Identifier.of("my-magic-tool"),
                Optional.of("MIT"),
                DependencyGroup.of(ImmutableMap.of(
                        Identifier.of("my-magic-lib"),
                        ExactSemanticVersion.of(SemanticVersion.of(4, 5, 6)),
                        Identifier.of("some-other-lib"),
                        ExactSemanticVersion.of(
                                SemanticVersion.of(4, 1),
                                SemanticVersion.of(4, 2)),
                        Identifier.of("awesome-lib"),
                        AnySemanticVersion.of())));

        final String serializedProject = Serializers.serialize(project);

        final Either<JsonParseException, Project> deserializedProject =
                Serializers.parseProject(serializedProject);

        assertEquals(Either.right(project), deserializedProject);
    }

    @org.junit.Test
    public void testProjectSerializer2() throws Exception {
        assertTrue(Serializers.parseProject("this is not valid").left().isPresent());
    }

    @org.junit.Test
    public void testProjectSerializer3() throws Exception {
        assertTrue(Serializers.parseProject("").left().isPresent());
    }
}
