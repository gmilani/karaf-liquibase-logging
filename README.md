# Karaf liquibase logging tests
This repository is for helping to test the Liquibase SLF4J bridge developed by Matt Bertolini (https://github.com/mattbertolini/liquibase-slf4j). Don't use this in a production environment.

## Build
### Native build
If you want to run Karaf natively, do a simple build:
```
mvn clean install
```

### Docker build
If you prefer use Docker (which I recommend), use the following compilation profile:

```
mvn clean install -Pdocker
```

It will build a Docker image named `karaf-liquibase-logging-karaf`.

## Run

### Natively 
Natively, you can start Karaf using
```
./karaf-liquibase-logging-karaf/target/assembly/bin/start
```

Then connect to the client
```
./karaf-liquibase-logging-karaf/target/assembly/bin/client
```

At the end, stop with
```
./karaf-liquibase-logging-karaf/target/assembly/bin/client
```

### Using Docker
```
docker run -it karaf-liquibase-logging-docker
```
It will directly open the Karaf console on the running container in interactive mode.

## Useful commands

Here are basic commands you can use in the Karaf console, for more information please read the official documentation: https://karaf.apache.org/manual/latest/#_available_commands
```
❯ docker run -it karaf-liquibase-logging-docker
karaf: Ignoring predefined value for KARAF_HOME
        __ __                  ____      
       / //_/____ __________ _/ __/      
      / ,<  / __ `/ ___/ __ `/ /_        
     / /| |/ /_/ / /  / /_/ / __/        
    /_/ |_|\__,_/_/   \__,_/_/         

  Apache Karaf (4.2.15)

Hit '<tab>' for a list of available commands
and '[cmd] --help' for help on a specific command.
Hit '<ctrl-d>' or type 'system:shutdown' or 'logout' to shutdown Karaf.

karaf@root()>      
```

To show the installed bundles, use `bundle:list` (or simply `list`)
```
karaf@root()> bundle:list
START LEVEL 100 , List Threshold: 50
ID │ State    │ Lvl │ Version            │ Name
───┼──────────┼─────┼────────────────────┼──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────
14 │ Active   │  80 │ 3.0.0              │ Expression Language 3.0 API
15 │ Active   │  80 │ 2.0.0.SP1          │ CDI APIs
16 │ Active   │  80 │ 1.2.2              │ javax.interceptor API
17 │ Active   │  80 │ 1.2                │ javax.transaction API
18 │ Active   │  80 │ 1.9.3.1            │ Apache ServiceMix :: Bundles :: jasypt
19 │ Active   │  80 │ 1.0.0.3            │ Apache ServiceMix :: Bundles :: javax.inject
20 │ Active   │  80 │ 1.5.0              │ OPS4J Pax JDBC Generic Driver Extender
21 │ Active   │  80 │ 1.5.0              │ OPS4J Pax JDBC Config
22 │ Active   │  80 │ 1.5.0              │ OPS4J Pax JDBC Pooling Support Base
23 │ Active   │  80 │ 1.0.0.201505202023 │ org.osgi:org.osgi.service.jdbc
27 │ Active   │  80 │ 1.0.0.SNAPSHOT     │ karaf-liquibase-logging-app
28 │ Resolved │  80 │ 4.1.0.SNAPSHOT     │ liquibase-slf4j, Hosts: 67
40 │ Active   │  80 │ 4.2.15             │ Apache Karaf :: OSGi Services :: Event
46 │ Active   │  80 │ 4.2.15             │ Apache Karaf :: JDBC :: Core
62 │ Active   │  80 │ 2.5.1              │ HSQLDB
67 │ Active   │  80 │ 4.4.3              │ liquibase-core, Fragments: 28
68 │ Active   │  80 │ 1.5.0              │ OPS4J Pax JDBC HSQLDB Driver Adapter
```

To show the logs, use `log:display`

You can display a bundle's headers with `bundle:headers <bundle-id>`
