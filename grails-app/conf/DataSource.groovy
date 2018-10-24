dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}

environments {
    production {
        dataSource {
            dbCreate = "do-nothing"
            url = "jdbc:mysql://gastosdbelian.ccbzkfdkg45w.sa-east-1.rds.amazonaws.com:3306/gastosdbelian?autoReconnect=true"
            username = "elianBravin"
            password = "admin123"
            properties {
                validationQuery = "select 1"
                testOnBorrow = false
                testOnReturn = false
                testWhileIdle = true
                timeBetweenEvictionRunsMillis = 1000 * 30
                numTestsPerEvictionRun = 3
                minEvictableIdleTimeMillis = 1000 * 15
                removeAbandoned = true
                removeAbandonedTimeout = 60 * 15
                maxWait = 100
                maxActive = 50 //número máximo de conexiones en el pool
                maxIdle = 25 //número máximo de conexiones ociosas en el pool
                minIdle = 5 //número mínimo de conexiones ociosas en el pool
                initialSize = 5 //tamaño inicial del pool
            }
        }
    }
    development {
        dataSource {
            dbCreate = "create-drop"
            driverClassName = "org.h2.Driver"
            dialect = "org.hibernate.dialect.H2Dialect"
            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            username = "sa"
            password = ""
        }
    }
    test {
        dataSource {
            dbCreate = "create-drop"
            driverClassName = "org.h2.Driver"
            dialect = "org.hibernate.dialect.H2Dialect"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            username = "sa"
            password = ""
        }
    }
}