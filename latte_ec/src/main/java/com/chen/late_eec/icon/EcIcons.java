package com.chen.late_eec.icon;

import com.joanzapata.iconify.Icon;

public enum EcIcons implements Icon {
    icon_scan('\ue602'),
    icon_ali_pay('\ue606');

    private char cjaracter;

    EcIcons(char cjaracter) {
        this.cjaracter = cjaracter;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return cjaracter;
    }
}
