Converts hibernate proxies to normal classes.

Useful for GWT+Hibernate projects to send objects loaded from database to GWT client.

**Example usage**

Spring AOP (applicationContext.xml):
```
<aop:aspectj-autoproxy />

<bean class="ru.dehibernator.DehibernateAdvice" />
```

Annotation on service method:
```
import ru.dehibernator.Dehibernate;

class DocumentServiceImpl implements DocumentService {
  @Autowired
  private DocumentRepository repository;

  @Dehibernate
  @Transactional
  @Override
  public List<Document> loadDocuments() {
    return repository.loadDocuments();
  }
}
```

**Maven dependencies**

```
<dependency>
  <groupId>log4j</groupId>
  <artifactId>log4j</artifactId>
  <version>1.2.16</version>
</dependency>
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-core</artifactId>
  <version>3.6.1.Final</version>
</dependency>
<dependency>
  <groupId>org.aspectj</groupId>
  <artifactId>aspectjrt</artifactId>
  <version>1.6.10</version>
</dependency>
```