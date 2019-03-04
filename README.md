# sbt-release-individual-example

This is a example showing how to release individual.

```sh
$ sbt 'project sub1' 'release with-defaults'                                                                 ✔  4830  00:18:57
Use of ~/.sbtconfig is deprecated, please migrate global settings to /usr/local/etc/sbtopts
[info] Loading settings from idea.sbt ...
[info] Loading global plugins from /Users/j5ik2o/.sbt/1.0/plugins
[info] Loading settings from plugins.sbt ...
[info] Loading project definition from /Users/j5ik2o/Sources/example-sbt-release/project
[info] Loading settings from version.sbt,build.sbt ...
[info] Loading settings from version.sbt ...
[info] Loading settings from version.sbt ...
[info] Set current project to example-sbt-release (in build file:/Users/j5ik2o/Sources/example-sbt-release/)
[info] Set current project to sub1 (in build file:/Users/j5ik2o/Sources/example-sbt-release/)
[info] Starting release process off commit: c7650606f9d9bd0adc9405aa88adf7c82f889dff
[info] Checking remote [origin] ...
[info] Updating ...
[info] Done updating.
[success] Total time: 0 s, completed 2019/03/05 0:19:30
[info] Setting version to '1.0.13'.
[info] Reapplying settings...
[info] Set current project to sub1 (in build file:/Users/j5ik2o/Sources/example-sbt-release/)
[info] [master e093289] Setting version to sub1-0.1.0-SNAPSHOT
[info]  1 file changed, 1 insertion(+), 1 deletion(-)
[info] Reapplying settings...
[info] Set current project to sub1 (in build file:/Users/j5ik2o/Sources/example-sbt-release/)
[info] Reapplying settings...
[info] Set current project to sub1 (in build file:/Users/j5ik2o/Sources/example-sbt-release/)
[info] Setting version to '1.0.14-SNAPSHOT'.
[info] Reapplying settings...
[info] Set current project to sub1 (in build file:/Users/j5ik2o/Sources/example-sbt-release/)
[info] [master ce7e047] Setting version to sub1-0.1.0-SNAPSHOT
[info]  1 file changed, 1 insertion(+), 1 deletion(-)
[info] To github.com:j5ik2o/sbt-release-example.git
[info]    c765060..ce7e047  master -> master
[info] To github.com:j5ik2o/sbt-release-example.git
[info]  * [new tag]         sub1-v1.0.13 -> sub1-v1.0.13
```

Note: the release of the top project does not include version bump of sub projects, in this configuration case.

```sh
$ sbt 'release with-defaults'
...
```

Bumped `./version`, but `sub1/version.sbt` and `sub2/version.sbt` not be bumped.
