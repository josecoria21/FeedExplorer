package com.josecoria.feedexplorer.ui.list;

import com.josecoria.feedexplorer.domain.usecase.GetProvidersUseCase;
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
public final class ProviderListViewModel_Factory implements Factory<ProviderListViewModel> {
  private final Provider<GetProvidersUseCase> getProvidersUseCaseProvider;

  public ProviderListViewModel_Factory(Provider<GetProvidersUseCase> getProvidersUseCaseProvider) {
    this.getProvidersUseCaseProvider = getProvidersUseCaseProvider;
  }

  @Override
  public ProviderListViewModel get() {
    return newInstance(getProvidersUseCaseProvider.get());
  }

  public static ProviderListViewModel_Factory create(
      Provider<GetProvidersUseCase> getProvidersUseCaseProvider) {
    return new ProviderListViewModel_Factory(getProvidersUseCaseProvider);
  }

  public static ProviderListViewModel newInstance(GetProvidersUseCase getProvidersUseCase) {
    return new ProviderListViewModel(getProvidersUseCase);
  }
}
