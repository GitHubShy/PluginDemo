# PluginDemo
Andoird插件调研
  这是个类似于插件的原理调研（重要的事情说三边，原理调研，非实战非实战非实战，网上应该有很多实战框架），第一版雏形，实现最基本功能：加载sd卡上的最简单view（没有应用res资源的，应用到res资源的在下一版加上）和apk。类似从服务器下载插件（view和apk）到sdcard然后加载view或apk。完美效果类似qq游戏和hotfix。
2016/9/18
 1.加载view待改进功能：需要插件和应用共同持有个一个接口，这样可以方便应用和插件的view交互。这个测试过暂时有bug;现在的原理是将需要的View导包为jar，并用
   sdk目录下的dx工具优化 “dx --dex --output = destination.jar origin.jar”然后放入sdcard，jar包的名字暂时需要和view的名字相同
 2.加载sd卡未安装apk这个功能参考网上，感觉是超级虚一方法，极其不实用，或者方法不对（apk需要按某种规则写），仅作参考
 3.下个版本需要解决问题1和研究res资源。
 4.希望天降一台性能好的服务器，能在源码下研究cm的主题切换功能（ResourcesManager等类）。

