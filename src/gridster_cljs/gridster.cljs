(ns gridster-cljs.gridster)

(def ^:private $ js/$)

(defn- get-api-object
  [el]
  (.data (. ($ el) gridster) "gridster"))

(defn create-grid
  [grid-el conf]
  (let [c (clj->js conf)]
    (.gridster ($ grid-el) c)))

(defn add-widget
  [grid-el html & [sizex sizey col row]]
  (let [$grid (get-api-object grid-el)]
    (-> (.add_widget $grid html sizex sizey col row)
        (.get))))

(defn resize-widget
  [grid-el widget & [sizex sizey callback]]
  (let [$grid (get-api-object grid-el)
        $widget ($ widget)]
    (-> (.resize_widget $grid $widget sizex sizey callback)
        (.get))))

(defn remove-widget
  [grid-el el & [callback]]
  (let [$grid (get-api-object grid-el)
        $el ($ el)]
    (.remove_widget $grid $el callback)))

(defn serialize
  [grid-el & [widgets]]
  (let [$grid (get-api-object grid-el)]
    (if (nil? widgets)
      (-> (.serialize $grid)
          (js->clj :keywordize-keys true))
      (-> (.serialize $grid ($ widgets))
          (js->clj :keywordize-keys true)))))

(defn serialize-changed
  [grid-el]
  (let [$grid (get-api-object grid-el)]
    (-> (.serialize_changed $grid)
        (js->clj :keywordize-keys true))))

(defn enable
  [grid-el]
  (let [$grid (get-api-object grid-el)]
    (.enable $grid)))

(defn disable
  [grid-el]
  (let [$grid (get-api-object grid-el)]
    (.disable $grid)))
