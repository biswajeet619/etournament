package com.etournament.proj.providers;

import com.etournament.proj.fetchers.GraphQLDataFetchers;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {

    @Autowired
    private GraphQLDataFetchers graphQLDataFetchers;

    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("graphql/schema.graphql");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        RuntimeWiring.Builder builder = RuntimeWiring.newRuntimeWiring();
        builder = buildMatchWiring(builder);
        builder = buildRegistrationWiring(builder);
        return builder
                .build();
    }

    private RuntimeWiring.Builder buildRegistrationWiring(RuntimeWiring.Builder builder) {
        return builder
                .type(newTypeWiring("Query")
                        .dataFetcher("registrations", graphQLDataFetchers.getAllRegistrations()))
                .type(newTypeWiring("Query")
                        .dataFetcher("registrationsByMatch", graphQLDataFetchers.getRegistrationByMatch()))
                .type(newTypeWiring("Query")
                        .dataFetcher("registrationsForUser", graphQLDataFetchers.getRegistrationForUser()))
                .type(newTypeWiring("Query")
                        .dataFetcher("registrationByMatchForUser", graphQLDataFetchers.getRegistrationByMatchForUser()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("createRegistration", graphQLDataFetchers.createRegistration()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("cancelRegistration", graphQLDataFetchers.cancelRegistration()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("updatePayment", graphQLDataFetchers.updatePayment()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("addPlayer", graphQLDataFetchers.addPlayer()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("removePlayer", graphQLDataFetchers.removePlayer()));
    }

    private RuntimeWiring.Builder buildMatchWiring(RuntimeWiring.Builder builder) {
        return builder
                .type(newTypeWiring("Query")
                        .dataFetcher("match", graphQLDataFetchers.getMatch()))
                .type(newTypeWiring("Query")
                        .dataFetcher("matches", graphQLDataFetchers.getAllMatches()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("createMatch", graphQLDataFetchers.createMatch()))
                .type(newTypeWiring("Mutation")
                        .dataFetcher("deleteMatch", graphQLDataFetchers.deleteMatch()));
    }

}

