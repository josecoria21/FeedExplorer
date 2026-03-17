package com.josecoria.feedexplorer.ui.detail;

import androidx.lifecycle.SavedStateHandle;
import com.josecoria.feedexplorer.domain.usecase.GetProviderByIdUseCase;
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
public final class ProviderDetailViewModel_Factory implements Factory<ProviderDetailViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetProviderByIdUseCase> getProviderByIdUseCaseProvider;

  public ProviderDetailViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetProviderByIdUseCase> getProviderByIdUseCaseProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getProviderByIdUseCaseProvider = getProviderByIdUseCaseProvider;
  }

  @Override
  public ProviderDetailViewModel get() {
    return newInstance(savedStateHandleProvider.get(), getProviderByIdUseCaseProvider.get());
  }

  public static ProviderDetailViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetProviderByIdUseCase> getProviderByIdUseCaseProvider) {
    return new ProviderDetailViewModel_Factory(savedStateHandleProvider, getProviderByIdUseCaseProvider);
  }

  public static ProviderDetailViewModel newInstance(SavedStateHandle savedStateHandle,
      GetProviderByIdUseCase getProviderByIdUseCase) {
    return new ProviderDetailViewModel(savedStateHandle, getProviderByIdUseCase);
  }
}
