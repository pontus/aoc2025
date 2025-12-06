(require '[clojure.repl :refer :all])
(use 'clojure.string)


(defn get-input [] (clojure.java.io/reader "input.txt"))

(defn i [n] (Long. n))


(defn opmap [s] (if (= "*" (trim s)) * +))

(def opindex (map count (re-seq #"[^ ] *" (last (line-seq (get-input))))))

(def numindex
  (let [c opindex
        w (dec (count c))]
    (loop [off 0
           i 0
           previous []]
      (if (< off w)
        (recur (inc off)
               (+ i (nth c off))
               (into previous [[i (+ i (nth c off))]]))
        (into previous [[i (+ i (nth c off))]])))))


(defn linetovec
  [l]
  (for [x (range (count opindex))]
    (let [spos (first (nth numindex x))
          epos (last (nth numindex x))]
      (subs l spos epos))))

(def alltext (map linetovec (line-seq (get-input))))
(def ops (map opmap (last alltext)))

(def numtext (take (dec (count alltext)) alltext))

(def nummiddle
  (vec (for [i (range (count ops))]
         (for [col (range (dec (nth opindex i)))]
           (vec (for [row (range (count numtext))]
                  (let [rowtext (nth numtext row)]
                    (if (< col (count (nth rowtext i)))
                      (subs (nth rowtext i) col (inc col))))))))))

(defn vecstrtoint [v] (printf "Converting %s\n" v) (i (trim (apply str v))))

(def numbers (for [i (range (count ops))] (map vecstrtoint (nth nummiddle i))))


(printf "Numbers %s\n" (vec numbers))

(def colvals
  (for [col (range (count (vec ops)))] (apply (nth ops col) (nth numbers col))))


(vec (for [col (range (count ops))] (printf "Counting %d\n" col)))
(printf "Colvals %s\n" (vec colvals))

(printf "%d\n" (apply + (vec colvals)))