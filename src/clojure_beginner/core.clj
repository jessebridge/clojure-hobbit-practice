(ns clojure-beginner.core
    (:gen-class))

(defn -main []
      (print "HEY"))


(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])


(defn matching-part
      [part]
      {:name (clojure.string/replace (:name part) #"^left-" "right-")
       :size (:size part)})

(defn symmetrize-body-parts
      "Expects a seq of maps that have a :name and :size"
      [asym-body-parts]
      (reduce
        (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
        []
        asym-body-parts))


(defn hit
      [asym-body-parts]
      "Expects a seq of maps that have :name and :size determines which part is hit"
      "Takes in the original list of body parts, semitrizes them, adds the numbers and decides which part is hit"
      (let [sym-parts (symmetrize-body-parts asym-body-parts)
            body-part-size-sum (reduce + (map :size sym-parts))
            ;body-part-names (reduce conj [] (map :name sym-parts))
            target (rand body-part-size-sum)]
           ;(print body-part-size-sum)
           ;(print body-part-names)
           (loop [[part & remaining] sym-parts
                  acumulated-size (:size part)]
                 (if (> acumulated-size target)
                   part
                   (recur remaining (+ acumulated-size (:size (first remaining))))))))


(defn string-join-example [string-one string-two]
      (str string-one " " string-two))

(defn reduce-vector-example [vector-of-strings]
      (reduce
        (fn [initial value]
            (str initial value " "))
        ""
        vector-of-strings))

(defn reduce-map-example [map-of-strings]
      (reduce
        (fn [currentstr [key value]]
            (str currentstr value " "))
        ""
        map-of-strings))

(defn map-simple-example [{:keys [a e g]}]
      (str a " " e " " g))

(defn map-2-level-deep-example [{{:keys [key1 key3 key10 key5]}:a}]
      (str key1 " " key3 " " key10 " " key5))

(defn map-multiple-level-deep-example [map-values]
      (let [{{:keys [a]}map-values} first-level
            {{:keys [bedrooms]}first-level} number-bedrooms]
        (integer number-bedrooms)))






"prints the body part that was hit"
(println (hit asym-hobbit-body-parts))
(println (string-join-example "hello" "Jesse"))
(println (reduce-vector-example ["hello" "its" "me" "Im" "in" "california" "dreaming" "about" "who" "we" "used" "to" "be"]))
(println (reduce-map-example {:a "Thanks" :b "for" :c "trying" :d "sweety" :e "but" :f "no" :g "luck"}))
(println (map-simple-example {:a "very" :c "blah" :e "cool" :g "kanye" :b "wow" :n "dude" :p "okay"}))
(println (map-2-level-deep-example {:a {:key1 "hello" :key2 "asdasd" :key3 "my" :key4 "asdasd" :key5 "jeff" :key10 "name"} :b "haha"}))
(println (map-multiple-level-deep-example {:a {:houseDetails {:kitchen "big" :bedrooms "5" :pool "no"}}:b "hey"}))
