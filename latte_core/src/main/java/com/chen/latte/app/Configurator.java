package com.chen.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

public class Configurator {
    private static final HashMap<String,Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    @SuppressWarnings("unchecked")
    public static Configurator getInstance(){
        return Holder.Instance;
    }

    final HashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    public final void configure(){
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    private static class Holder{
        private static final Configurator Instance = new Configurator();
    }

    private void initIcons(){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i =1;i < ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
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
