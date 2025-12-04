(require '[clojure.repl :refer :all])

(defn get-input [] (clojure.java.io/reader "input.txt"))





(defn width [room] (count (first room)))
(defn length [room] (count room))

(defn getat
  [room x y]
  (if (or (< x 0) (< y 0) (>= y (length room)) (>= x (width room)))
    '.'
    (aget (aget room y) x)))

(defn clearat
  [room x y]
  ;;  (do
  ;; (printf "Clearing at %d %d\n" x y)
  (aset (aget room y) x \.))


(defn linetovec
  [l]
  ;;   (printf "Line: %s \n" l)
  (if (= 0 (count l)) [] (into-array (char-array l))))

(defn getroom [] (into-array (map linetovec (line-seq (get-input)))))

(def room (getroom))

(defn paperat
  [room x y]
  ;;  (printf "Checking at %d,%d gives %s\n" x y (getat room x y))
  (= \@ (getat room x y)))


(defn countat [room x y] (if (paperat room x y) 1 0))

(defn countaround
  [room x y]
  (+ (countat room (dec x) (dec y))
     (countat room (dec x) y)
     (countat room (dec x) (inc y))
     (countat room x (dec y))
     (countat room x (inc y))
     (countat room (inc x) (dec y))
     (countat room (inc x) y)
     (countat room (inc x) (inc y))))

(defn checkaccess
  [room x y]
  (if (= \@ (getat room x y))
    (let [surround (countaround room x y)]
      ;; (printf "Checkaccess at %d,%d returns %d\n" x y surround)
      (if (< surround 4) (do (clearat room x y) true)))
    false))


(printf "Room is %d x %d\n\n" (width room) (length room))

;; (printf "At 1,1 we have %s\n\n" (getat room 1 1))

(def startscore
  (apply +
    (for [x (range (width room))
          y (range (length room))]
      (if (= \. (getat room x y)) 1 0))))

(while true
  (do (vec (for [x (range (width room))
                 y (range (length room))]
             (if (checkaccess room x y) 1 0)))
      (let [score (apply +
                    (for [x (range (width room))
                          y (range (length room))]
                      (if (= \. (getat room x y)) 1 0)))]
        (printf "startscore %d score now %d\n" startscore score))))
