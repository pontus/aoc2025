(require '[clojure.repl :refer :all])
(use 'clojure.string)


(defn get-input [] (clojure.java.io/reader "input.txt"))

(defn i [n] (Long. n))

(defn linetovec [l] (let [v (split (trim l) #" +")] v))

(defn opmap [s] (if (= "*" s) * +))

(def alltext (map linetovec (line-seq (get-input))))
(def numbers (map (fn [x] (map i x)) (take (dec (count alltext)) alltext)))
(def ops (map opmap (last alltext)))


(def colvals
  (for [col (range (count (vec ops)))]
    (apply (nth ops col) (map (fn [x] (nth x col)) numbers))))


(vec (for [col (range (count ops))] (printf "Counting %d\n" col)))
(printf "Colvals %s\n" (vec colvals))

(printf "%d\n" (apply + (vec colvals)))