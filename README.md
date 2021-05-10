# Popis implementace

Jednoduchá implementace SSDP klienta. Klient umí přijímat všechny SSDP multicast zprávy které se přijmou na zařízení. 
Obsah přijatých zpráv je vypisován do konzole.

Kromě přijímání všech zpráv je v prográmku implementován mechanismus vyhledávání. Podle zadaného parametru program posílá multicast SSDP discovery zprávu na zadanou ST
skupinu a poté zpracová unicast zprávy přijímané zpět jako odpoveď na discovery.

# Spuštení
Pro spuštění programu je potřeba mít naistalovanou javu ve verzi 11

Program se spouští v příkazovém řádku následujícím příkazem

```shell
java -jar <path to jar file> <ST parametr> <discovery verbose (true/false)>
```

* **path to jar file** - cesta k souboru jar (viz přiložený jar v repu) 
* **ST parametr** - udává skupinu zařízení, které se mají vyhledávat
* **discovery verbose (true/false)** -  určuje jestli se vypíší pouze ip adresy nalezených ip adres (zařízení vyhledané na základě ST) nebo kompletní info doručené ve zprávě
