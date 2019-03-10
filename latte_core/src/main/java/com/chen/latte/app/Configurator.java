package com.chen.latte.app;

import java.util.WeakHashMap;

public class Configurator {
    private static final WeakHashMap<String,Object> LATTE_CONFIGS = new WeakHashMap<>();

    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    public static Configurator getInstance(){
        return Holder.Instance;
    }

    final WeakHashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    public final void configure(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    private static class Holder{
        private static final Configurator Instance = new Configurator();
    }

    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    private void checkfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call config");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfigguration(Enum<ConfigType> key){
        checkfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }


}
