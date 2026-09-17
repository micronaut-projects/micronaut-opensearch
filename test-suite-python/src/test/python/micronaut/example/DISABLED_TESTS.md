# Python Docs Disabled Test Inventory

This file tracks the Python documentation examples under `test-suite-python`
that are present but disabled, or that deviate from the Java example because the direct port does
not compile or does not behave like the Java example yet. It is the bug-fixing task list for
the Python compiler (`micronaut-inject-python` / `micronaut-context-python`); every row references a
`TODO(python)` comment in the sources or a workaround described below.

The Python examples are compiled by every build and their tests run with
`./gradlew pythonCheck -Ppython-ci` (the "Python CI" GitHub workflow), which needs a container
runtime for the OpenSearch test container.

## Reconciliation

- Last generated active `@Disabled` count: 0.
- Last generated command: `rg -n "@Disabled\(" test-suite-python/src/test/python`.
- Last full-suite command: `./gradlew :test-suite-python:test -Ppython-ci`.
- Last full-suite result: build successful, 5 tests executed, 0 skipped, 0 failures.

## Migration Rules

- Do not define local copies of Micronaut annotation helpers or custom annotation shims in docs snippets.
  The Micronaut and OpenSearch classes are imported from their Java packages (`micronaut.context.annotation`,
  `jakarta.inject`, `org.opensearch.client.opensearch`, `org.opensearch.client.transport.aws`).
- Methods are snake_case (`save_movie`, `search_movies`); `MovieService` is an `ABC` implemented by the
  `@Singleton` `MovieServiceImpl`; `Movie` is an `@Introspected` dataclass; `AppConfiguration` is a
  `@ConfigurationProperties("app")` class with a `movies_index_name` attribute (`app.movies-index-name`);
  logging uses Python's `logging` module; Java exceptions are caught by their imported type
  (`from java.lang import Exception as JavaException`).
- The OpenSearch client's builder lambdas (`client.search(lambda s: ...)`) are plain Python lambdas.
- The tests are `@MicronautTest` classes; the OpenSearch container address is supplied to the `opensearch`
  environment by the Java `micronaut.example.support.OpenSearchTestConfigurer` (`@ContextConfigurer`).

## Active `@Disabled` Tests

None.

## Commented Unsupported Snippet Ports

None.

## Workarounds Kept In Snippets

| Target | Reason |
| --- | --- |
| `micronaut.example.support.OpenSearchTestConfigurer` (Java, `src/test/java`) | `TestPropertyProvider.getProperties()` is called by Micronaut Test before the application context, and with it the GraalPy runtime, exists, so a Python test class cannot provide the container properties; the `@ContextConfigurer` adding the property source in `configure(ApplicationContext)` is written in Java. |

## Intentionally Unsupported Snippet Targets

None.

## java.type usages

| Target | Reason |
| --- | --- |
| `micronaut.example.service.MovieServiceImpl` (`MovieDocument = java.type("micronaut.example.service.Movie")`) | `OpenSearchClient.search(..., Class<TDocument>)` takes the document class at runtime; the Python class `Movie` cannot be passed to Java as a `java.lang.Class`, so the generated Java class of the dataclass is passed instead. |
