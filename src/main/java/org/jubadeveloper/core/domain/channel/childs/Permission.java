package org.jubadeveloper.core.domain.channel.childs;

import jakarta.persistence.*;
import org.jubadeveloper.core.domain.channel.Channel;
import org.jubadeveloper.core.domain.user.User;

@Embeddable
public class Permission {
    private Boolean write;
    @Column(name = "'join'")
    private Boolean join;
    public Permission () {}
    public Permission(Boolean write, Boolean join) {
        this.write = write;
        this.join = join;
    }

    public Boolean getWrite() {
        return write;
    }

    public void setWrite(Boolean write) {
        this.write = write;
    }

    public Boolean getJoin() {
        return join;
    }

    public void setJoin(Boolean join) {
        this.join = join;
    }
}
