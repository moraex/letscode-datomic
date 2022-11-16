(ns aula.exercicio
  (:require [datomic.client.api :as d]))

;; 1 - adicionar schema para termos artista como uma entidade separada com id e nome

(def artist [{:db/ident :artist/id
             :db/cardinality :db.cardinality/one
             :db/unique :db.unique/identity
             :db/valueType :db.type/long}
            {:db/ident :artist/nome
             :db/cardinality :db.cardinality/one
             :db/valueType :db.type/string}])

(d/transact conn {:tx-data artist})

(d/transact conn {:tx-data [{:artist/id 1
                             :artist/nome "Seu Jorge"}
                            {:artist/id 2
                             :artist/nome "Gal Costa"}
                            {:artist/id 3
                             :artist/nome "Emicida"}
                            {:artist/id 4
                             :artist/nome "Iza"}]})

(def album [{:db/ident :album/id
             :db/cardinality :db.cardinality/one
             :db/unique :db.unique/identity
             :db/valueType :db.type/long}
            {:db/ident :album/nome
             :db/cardinality :db.cardinality/one
             :db/valueType :db.type/string}
            {:db/ident :album/artista
             :db/cardinality :db.cardinality/one
             :db/valueType :db.type/ref}])

;; criando o schema no db
(d/transact conn {:tx-data album})

(d/transact conn {:tx-data [{:album/id 1
                             :album/nome "American Idiot"
                             :album/artista [:artist/id 1]}
                            {:album/id 2
                             :album/nome "Amar Elo"
                             :album/artista [:artist/id 2]}
                            {:album/id 3
                             :album/nome "Multitude"
                             :album/artista [:artist/id 3]}]})

;; 2 - criar query para listar albuns e seus artistas (uma query para cada)
<<<<<<< HEAD
;; query

(d/q '[:find (pull ?e [*])
       :where [?e :artist/nome]]
     (d/db conn))

(d/q '[:find (pull ?e [*])
       :where [?e :album/nome]]
     (d/db conn))

;; 3 - criar query para listar um album por nome
(d/q '[:find (pull ?e [*])
       :where [?e :album/nome "American Idiot"]]
     (d/db conn))

;; 4 - listar albuns por artista. (bônus
=======
;; 3 - criar query para listar albuns e seus artistas (album e o artista em uma só estrutura de mapa)
;; 4 - criar query para listar um album por nome
;; 5 - listar albuns por artista. (bônus)
>>>>>>> 696bf5cd4b3037adb56b1dd7336e4a7127635df9

;; referência -- https://docs.datomic.com/cloud/query/query-executing.html


(comment
  (def client (d/client {:server-type :dev-local
                         :system "dev"}))
  
  (d/create-database client {:db-name "artists"})

  (d/delete-database client {:db-name "artists"})

  (def conn (d/connect client {:db-name "artists"}))
  (def db (d/db conn))
  )