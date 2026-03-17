package com.josecoria.feedexplorer.data.di;

import com.josecoria.feedexplorer.data.remote.ProviderApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideProviderApiFactory implements Factory<ProviderApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideProviderApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ProviderApi get() {
    return provideProviderApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideProviderApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideProviderApiFactory(retrofitProvider);
  }

  public static ProviderApi provideProviderApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideProviderApi(retrofit));
  }
}
