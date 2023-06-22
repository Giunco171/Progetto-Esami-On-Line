In seguito all'iniezione delle dipendenze descritte nel Pom.xml (solo lato server) è necessario modificare il metodo "hashCode()" della inner class EsamiOnLine.Appello come descritto alla fine delle relazione.
Nei file relativi il server sono presenti tre directory contenenti dei file json, utili per testare il funzionamento del server e del client.
Occorre, ovviamente, avviare prima il server e poi il client.
Il socket si instaura sulla porta 8989, ciò è hardcoded sia lato server che lato client.
Occorre avviare i file "main" sia del server che del client per accedere a tutte le funzionalità offerte.
I test Junit sono presenti nelle cartelle "Test".
