package fr.mizu.littlegameslib.Internationalization;

import fr.mizu.littlegameslib.config.ConfigFile;
import org.bukkit.plugin.Plugin;

public class I18N {
    private ConfigFile I18NConfig;

    public I18N(String name, Plugin plugin){
        this.I18NConfig = new ConfigFile(plugin.getDataFolder()+"/I18N/"+name+".yml");
    }

    public String getTranslatedMessage(String message) {
        return I18NConfig.getString(message);
    }
}
