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
public final class GetProviderByIdUseCase_Factory implements Factory<GetProviderByIdUseCase> {
  private final Provider<ProviderRepository> repositoryProvider;

  public GetProviderByIdUseCase_Factory(Provider<ProviderRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetProviderByIdUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetProviderByIdUseCase_Factory create(
      Provider<ProviderRepository> repositoryProvider) {
    return new GetProviderByIdUseCase_Factory(repositoryProvider);
  }

  public static GetProviderByIdUseCase newInstance(ProviderRepository repository) {
    return new GetProviderByIdUseCase(repository);
  }
}
