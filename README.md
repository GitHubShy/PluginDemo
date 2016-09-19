# PluginDemo
Andoird插件调研
2016/9/18
 1.第一版雏形，实现最基本功能：加载sd卡上的最简单view和apk(类似从服务器下载插件到sdcard然后加载view或apk，加载apk这个功能略鸡肋，太虚)
 2.加载view待改进功能：需要插件和应用共同持有个一个接口，这样可以方便应用和插件的view交互。这个测试过暂时有bug。
 3.jar包需要dx --dex --output=loader_dex.jar loader.jar优化

