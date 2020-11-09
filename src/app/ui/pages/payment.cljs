(ns app.ui.pages.payment
  (:require [keechma.next.helix.core :refer [with-keechma dispatch call]]
            [keechma.next.helix.lib :refer [defnc]]
            [helix.core :as hx :refer [$ <> suspense]]
            [helix.dom :as d]
            ["react" :as react]
            ["react-dom" :as rdom]
            [app.ui.components.inputs :refer [wrapped-input]]
            [keechma.next.controllers.router :as router]))

(defnc PaymentRenderer
  [props]
  (d/div
   {:class "auth-page"}
   (d/div
    {:class "container page"}
    (d/div
     {:class "row"}
     (d/div
      {:class "col-md-6 offset-md-3 col-xs-12"}
      (d/h1
       {:class "text-xs-center"}
       "PAYMENT")
      (d/div {:style {:display "flex"}}
             (d/img {:src "../assets/mastercard3.png" })
             (d/img {:src "../assets/american-express.png"})
             (d/img {:src "../assets/visa.png"}))
      (d/p
       {:class "text-xs-left"}
       "Credit cards:")
      (d/hr {:style {:width "100%"}})
      (d/form 
        {:on-submit (fn [e]
                      (.preventDefault e)
                      (dispatch props :payment-form :keechma.form/submit))}
       (d/h6 "Name:")
       (d/div {:style {:display "flex" 
                       :justify-content "space-between"}}

              (d/div {:class "col-6"}
                     (wrapped-input {:keechma.form/controller :payment-form
                                     :style {:border-bottom-style "solid"}
                                     :input/type :text
                                     :input/attr :firstname
                                     :placeholder "First name"}))
              (d/div {:class "col-6"}
                     (wrapped-input {:keechma.form/controller :payment-form
                                     :input/type :text
                                     :input/attr :lastname
                                     :placeholder "Last name"})))
       (d/h6 "Credit Card Number:")
       (d/div 
        (wrapped-input {:keechma.form/controller :payment-form
                        :input/type :text
                        :input/attr :creditcardnumber
                        :placeholder "9999 9999 9999 9999"}))
       (d/div {:style {:display "flex"
                       :justify-content "space-between"}}
              (d/div 
               {:class "col-6"}
               (d/h6 "Expiration date:")
               (wrapped-input {:keechma.form/controller :payment-form
                               :input/type :text
                               :input/attr :expiredate
                               :placeholder "MM/YYYY"}))
              (d/div {:class "col-6"}
                     (d/h6 "Security Code:")
                     (wrapped-input {:keechma.form/controller :payment-form
                                     :input/type :text
                                     :input/attr :securitycode
                                     :placeholder "CVV"})
                     ))
       (d/button
        {:class "btn btn-lg btn-primary pull-xs-right"} "Confirm")
       ))))
      )
      )


(def Payment (with-keechma PaymentRenderer))