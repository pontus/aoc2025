(require '[clojure.repl :refer :all])
(use 'clojure.string)

(defn get-input [] (clojure.java.io/reader "input.txt"))

(def alltext (vec (line-seq (get-input))))
(def freshstrings (subvec alltext 0 (.indexOf alltext "")))

(defn i [n] (Long. n))

(defn rangetovec [s] (let [v (split s #"-")] [(i (get v 0)) (i (get v 1))]))

(def fresh (vec (sort (map rangetovec freshstrings))))
(printf "Start: %d\n" (count fresh))

; Checks whatever v has any overlapping items
(defn overlapping
  [v]
  (if (= (second v) nil)
    false
    (let [f (first v)
          s (second v)]
      (if (>= (get f 1) (get s 0)) true (overlapping (subvec v 1))))))


(defn joinslots
  [v]
  (if (= (second v) nil) ; End of data?
    v ; yes, stop recursion
    (let [f (first v)
          s (second v)]
      (if (>= (get f 1) (get s 0))
        (into [[(get f 0) (max (get f 1) (get s 1))]] (joinslots (subvec v 2)))
        (into [f] (joinslots (subvec v 1)))))))



(while (overlapping fresh)
  (printf "Fresh is %s\n" fresh)
  (def fresh (joinslots fresh)))

(printf "Fresh is %s\n" fresh)

(def nums (vec (map (fn [n] (inc (- (second n) (first n)))) fresh)))
(def sum (reduce + nums))

(printf "After reducing: %d\nFresh: %s\n Nums: %s\nSum: %d\n\n"
        (count fresh)
        fresh
        nums
        sum)