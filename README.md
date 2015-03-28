# Circulation
POC akka monothread with remote actor and REST asynchrone (weld + resteasy)

## Run the webservices :
(with jetty-maven-plugin "jetty:run" for modules)
-bareme.server (port 9003)
-client.server (asynchrone) (port 9004)
-rail.server (port 9005)
-train.server (port 9006)
-translate.server (port 9007)

## Run the first akka :
fr.cp.reseau.actor.LaunchReseau (port 2552)

INFO  fr.cp.reseau.actor.LaunchReseau  [main] - Thread[main,5,main]
INFO  fr.cp.reseau.actor.ReseauActor  [reseauSystem-my-dispatcher2-14] - ReseauActor Thread[reseauSystem-my-dispatcher2-14,5,main]
INFO  fr.cp.reseau.actor.LaunchReseau  [main] - Actor[akka://reseauSystem/user/reseau#-1871022740]
INFO  fr.cp.reseau.actor.TrainActor  [reseauSystem-my-dispatcher2-14] - TrainActor Thread[reseauSystem-my-dispatcher2-14,5,main]
INFO  fr.cp.reseau.actor.RailActor  [reseauSystem-my-dispatcher2-14] - RailActor Thread[reseauSystem-my-dispatcher2-14,5,main]

## Run the second akka :
fr.cp.circulation.Circulation (port 4711)

INFO  fr.cp.circulation.actor.Listener  [circulation-my-dispatcher-15] - OK c 1 120 rail 1 train 1 Tr:comment47
INFO  fr.cp.circulation.actor.Listener  [circulation-my-dispatcher-15] - OK c 1 120 rail 1 train 1 Tr:comment82
INFO  fr.cp.circulation.actor.Listener  [circulation-my-dispatcher-15] - OK c 1 120 rail 1 train 1 Tr:comment98
INFO  fr.cp.circulation.actor.Listener  [circulation-my-dispatcher-15] - OK c 1 120 rail 1 train 1 Tr:comment99
INFO  fr.cp.circulation.actor.Listener  [circulation-my-dispatcher-15] - OK c 1 120 rail 1 train 1 Tr:comment84
INFO  fr.cp.circulation.actor.Listener  [circulation-my-dispatcher-15] - OK c 1 120 rail 1 train 1 Tr:comment74
INFO  fr.cp.circulation.actor.Listener  [circulation-my-dispatcher-15] - OK c 1 120 rail 1 train 1 Tr:comment60
INFO  fr.cp.circulation.actor.Listener  [circulation-my-dispatcher-15] - OK c 1 120 rail 1 train 1 Tr:comment59
INFO  fr.cp.circulation.actor.Listener  [circulation-my-dispatcher-15] - OK c 1 120 rail 1 train 1 Tr:comment5
INFO  fr.cp.circulation.actor.Listener  [circulation-my-dispatcher-15] - OK c 1 120 rail 1 train 1 Tr:comment97
INFO  fr.cp.circulation.actor.Listener  [circulation-my-dispatcher-15] - OK c 1 120 rail 1 train 1 Tr:comment38
INFO  fr.cp.circulation.actor.Listener  [circulation-my-dispatcher-15] - OK c 1 120 rail 1 train 1 Tr:comment71
INFO  fr.cp.circulation.actor.Listener  [circulation-my-dispatcher-15] - OK c 1 120 rail 1 train 1 Tr:comment89
etc.

