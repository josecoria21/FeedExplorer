package com.josecoria.feedexplorer.domain.usecase;

import com.josecoria.feedexplorer.domain.repository.ProviderRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class GetProvidersUseCase_Factory implements Factory<GetProvidersUseCase> {
  private final Provider<ProviderRepository> repositoryProvider;

  public GetProvidersUseCase_Factory(Provider<ProviderRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetProvidersUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetProvidersUseCase_Factory create(
      Provider<ProviderRepository> repositoryProvider) {
    return new GetProvidersUseCase_Factory(repositoryProvider);
  }

  public static GetProvidersUseCase newInstance(ProviderRepository repository) {
    return new GetProvidersUseCase(repository);
  }
}
