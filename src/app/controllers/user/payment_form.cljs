(ns app.controllers.user.payment-form
  (:require [keechma.next.controller :as ctrl]
            [keechma.next.controllers.pipelines :as pipelines]
            [keechma.pipelines.core :as pp :refer-macros [pipeline!]]
            [app.api :as api]
            [promesa.core :as p]
            [keechma.next.controllers.form :as form]
            [app.validators :as v]
            [promesa.core :as p]
            [keechma.next.controllers.router :as router]))

(derive :user/payment-form ::pipelines/controller)

(def pipelines
  {:keechma.form/submit-data (pipeline! [value ctrl]
                                        (println value))})


(defmethod ctrl/prep :user/payment-form [ctrl]
  (pipelines/register ctrl (form/wrap pipelines (v/to-validator {}))))
