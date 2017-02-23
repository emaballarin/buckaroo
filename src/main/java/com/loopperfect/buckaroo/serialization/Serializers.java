package com.loopperfect.buckaroo.serialization;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.loopperfect.buckaroo.*;

import java.io.StringReader;

public final class Serializers {

    private Serializers() {

    }

    private static <T> Either<JsonParseException, T> parse(final String x, final Class<T> clazz) {
        Preconditions.checkNotNull(x);
        Preconditions.checkNotNull(clazz);
        try {
            final T t = gson.fromJson(
                    new EmptyStringFailFastJsonReader(new StringReader(x)), clazz);
            return Either.right(t);
        } catch (final JsonParseException e) {
            return Either.left(e);
        }
    }

    public static Either<JsonParseException, Project> parseProject(final String x) {
        return parse(x, Project.class);
    }

    public static String serialize(final Project project) {
        Preconditions.checkNotNull(project);
        return gson.toJson(project);
    }

    public static Either<JsonParseException, BuckarooConfig> parseConfig(final String x) {
        return parse(x, BuckarooConfig.class);
    }

    public static String serialize(final Recipe recipe) {
        Preconditions.checkNotNull(recipe);
        return gson.toJson(recipe);
    }

    public static Either<JsonParseException, Recipe> parseRecipe(final String x) {
        return parse(x, Recipe.class);
    }

    public static String serialize(final BuckarooConfig config) {
        Preconditions.checkNotNull(config);
        return gson.toJson(config);
    }

    public static Either<JsonParseException, Dependency> parseDependency(final String x) {
        return parse(x, Dependency.class);
    }

    public static String serialize(final Dependency dependency) {
        Preconditions.checkNotNull(dependency);
        return gson.toJson(dependency);
    }

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(SemanticVersion.class, new SemanticVersionSerializer())
            .registerTypeAdapter(SemanticVersion.class, new SemanticVersionDeserializer())
            .registerTypeAdapter(GitCommit.class, new GitCommitSerializer())
            .registerTypeAdapter(GitCommit.class, new GitCommitDeserializer())
            .registerTypeAdapter(SemanticVersionRequirement.class, new SemanticVersionRequirementSerializer())
            .registerTypeAdapter(SemanticVersionRequirement.class, new SemanticVersionRequirementDeserializer())
            .registerTypeAdapter(Identifier.class, new IdentifierSerializer())
            .registerTypeAdapter(Identifier.class, new IdentifierDeserializer())
            .registerTypeAdapter(Dependency.class, new DependencySerializer())
            .registerTypeAdapter(Dependency.class, new DependencyDeserializer())
            .registerTypeAdapter(DependencyGroup.class, new DependencyGroupSerializer())
            .registerTypeAdapter(DependencyGroup.class, new DependencyGroupDeserializer())
            .registerTypeAdapter(RecipeVersion.class, new RecipeVersionSerializer())
            .registerTypeAdapter(RecipeVersion.class, new RecipeVersionDeserializer())
            .registerTypeAdapter(Recipe.class, new RecipeSerializer())
            .registerTypeAdapter(Recipe.class, new RecipeDeserializer())
            .registerTypeAdapter(Project.class, new ProjectSerializer())
            .registerTypeAdapter(Project.class, new ProjectDeserializer())
            .registerTypeAdapter(BuckarooConfig.class, new BuckarooConfigSerializer())
            .registerTypeAdapter(BuckarooConfig.class, new BuckarooConfigDeserializer())
            .registerTypeAdapter(RemoteCookBook.class, new RemoteCookBookSerializer())
            .registerTypeAdapter(RemoteCookBook.class, new RemoteCookBookDeserializer())
            .setPrettyPrinting()
            .create();
}
