package com.josecoria.feedexplorer.data.repository;

import com.josecoria.feedexplorer.data.remote.ProviderApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ProviderRepositoryImpl_Factory implements Factory<ProviderRepositoryImpl> {
  private final Provider<ProviderApi> apiProvider;

  public ProviderRepositoryImpl_Factory(Provider<ProviderApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public ProviderRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static ProviderRepositoryImpl_Factory create(Provider<ProviderApi> apiProvider) {
    return new ProviderRepositoryImpl_Factory(apiProvider);
  }

  public static ProviderRepositoryImpl newInstance(ProviderApi api) {
    return new ProviderRepositoryImpl(api);
  }
}
