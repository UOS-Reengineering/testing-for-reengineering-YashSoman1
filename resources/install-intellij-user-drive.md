# How to install IntelliJ 2022.3 on your shared drive

By default, `Software Center` only provides IntelliJ 2019.
Although it works well for most of the cases, if you want to use IntelliJ 2022
as you do in your own laptop, for example, you can install it on the shared drive.

My shared drive path is `/u/Teaching/COM3523/2022-23`, and I will show you how to install IntelliJ 2022 there.

First, you need to download the `zip` version of IntelliJ from the website: https://www.jetbrains.com/idea/download.
If you want, you can just download this [IntelliJ IDEA Community Edition 2022.3](https://github.com/JetBrains/intellij-community/archive/idea/223.8836.41.zip).

Once the download completed, extract the content to the shared drive (i.e., `/u/Teaching/COM3523/2022-23` in my case).
It will take some time (~10 mins) to extract all the files.

Then, you should be able to see a new folder `ideaIC-2022.3.3.win` under the shared drive.
Inside, you will see `ideaIC-2022.3.3.win/bin/idea64.exe`. Executing this file will open IntelliJ 2022.3.

![Git bash path setting](/resources/intellij2022-bin.png)

### Tip: How to use git bash as a terminal in intelliJ

You might notice that the "terminal" in IntelliJ is by default PowerShell in Windows.
You may want to use GitBash instead of it.
It's quite simple to change the configuration.

In IntelliJ, go to "Files" -> "Settings" -> "Tools" -> "Terminal".

On the right panel, go to "Application Settings" -> "Shall path".

Just update the Shall path to `C:\Program Files\Git\bin\bash.exe` and save the configuration.

![Git bash path setting](/resources/git-bash.png)

If you close and re-open a new terminal, it will be GitBash.
