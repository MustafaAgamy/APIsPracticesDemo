package Interfaces;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.HotReload;
import org.aeonbits.owner.Config.HotReloadType;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@HotReload(
        type = HotReloadType.ASYNC
)
@LoadPolicy(LoadType.MERGE)
public interface FrameProperties extends Config {
    SetProperty set();

    public interface SetProperty {
    }
}
