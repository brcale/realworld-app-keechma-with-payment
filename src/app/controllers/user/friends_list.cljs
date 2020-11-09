(ns app.controllers.user.friends-list
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [keechma.next.controllers.router :as router]
            [app.api :as api]
            ))

(derive :user/friends-list ::pipelines/controller)

(def get-friends-list
  (-> (pipeline! [value {:keys [state* deps-state*] :as ctrl}]
                 (let [{:keys [router jwt current-user]} @deps-state*
                       _ (println current-user)]
                   {:user (:username current-user) :jwt jwt})
                 (api/user-get value)
                 (println "friends-list kontroler" value))
      
      (pp/set-queue :load-user)
      pp/restartable))

(def pipelines
  {:keechma.on/start get-friends-list })

(defmethod ctrl/prep :user/friends-list [ctrl]
  (pipelines/register ctrl pipelines))
