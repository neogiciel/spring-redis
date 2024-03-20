<h1>Application Quarkus Redis MySQL</h1>
<img src="https://upload.wikimedia.org/wikipedia/fr/thumb/6/6b/Redis_Logo.svg/701px-Redis_Logo.svg.png?20190421180155" height=160px>
<p>
Mise en place d'un cache distribué Redis avec MysQL
</p>
<h2>Mise en place</h2><br>
Ajout des dépendences<br>
<h2>Pom.xml</h2><br>
<p>
<dependency>
  <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

<h2>application.properties</h2><br>
<p>
#base de donnée redis
redis.host=localhost
redis.port=6379
</p>
<h2>Fichier de Configuration d'une base Redis</h2><br>
@EnableCaching
public class RedisConfig {
 
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                .withCacheConfiguration("myCache",
                        RedisCacheConfiguration.defaultCacheConfig()
                                // .entryTtl(Duration.ofMinutes(10))
                                .entryTtl(Duration.ofSeconds(60)))
                .build();
    }
 
}
<h2>RestController</h2><br>
Vous trouverez ci-dessous un exemple de controller REST permettant d'effectuer la gestion du cache
<li>L'annotation  @Cacheable(value = "nomDuCache", key = "NomCle") permet de sauvegarder automatiquement les données dans notre base Redis</li><br>
<li>L'annotation  @CacheEvict(value = "myCache", allEntries = true) permet d'invalide la totalité du cache</li><br>
<li>L'annotation  @CacheEvict(value = "myCache", key = "NomCle") permet d'invalide une clé en particulier</li><br>
Il est à noter que la réponse renvoyer par nos différents appels REST doit être absolument sérialisables en base redis, d'ou le fait de renvoyer une String sous forme de JSon

<p>

@RestController
@RequestMapping("/cache")
public class ApiController {
 
    // BddManager
    // Manager de Gestion de notre Base de données
    @Autowired
    BddManager bdd;
 
     
    /*
     * listepersonne
     */
    @GetMapping(value = "/listepersonne", produces = "application/json")
    @Cacheable(value = "myCache", key = "'api-getListPersonnes_'")
    public String listepersonne() {
         
        //Requete en base de données
        List<Personne> liste = bdd.getListPersonnes();
         
        if (liste.size() > 0) {
            JSONArray jsonArray = new Personne().totListeJSON(liste);
            return jsonArray.toString();
        }
        return getJSON("nb", "0").toString();
    }
 
    /*
     * personne
     */
    @GetMapping(value = "/personne/{id}", produces = "application/json")
    @Cacheable(value = "myCache", key = "'api-getPersonneFromId_' + #id")
    public String personne(@PathVariable int id) {
        //Requête en base de données
        Personne personne = (Personne) bdd.getPersonneFromId(id);
        return personne.toJSON(personne).toString();
    }
 
     
    /*
     * Invalidation du cache
     */
    @GetMapping(value = "/invalidate", produces = "application/json")
    @CacheEvict(value = "myCache", allEntries = true)
    public String invalidate() {
        return getJSON("invalidate", "ok").toString();
    }
 
    /*
     * getJSON
     */
    public JSONObject getJSON(String value, String key) {
        JSONObject obj = new JSONObject();
        obj.put(value, key);
        return obj;
    }
 
}

</p>

<h1>Compilation et Lancement</h1>
<p>
Clear: <b>mvn clean</b><br>
Mise à jour des dependences: <b>mvn dependency:resolve</b><br>
Compilation et Lancement: <b>mvn quarkus:dev</b>
Url du service: http://localhost:8080<br>  
</p>
