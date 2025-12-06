(require '[clojure.repl :refer :all])
(use 'clojure.string)

(defn get-input [] (clojure.java.io/reader "input.txt"))

(def alltext (vec (line-seq (get-input))))
(def fresh (subvec alltext 0 (.indexOf alltext "")))
(def avail (subvec alltext (inc (.indexOf alltext ""))))

(defn i [n] (Long. n))

(defn freshtest
  [id]
  (fn [l]
    (let [v (split l #"-")]
      (and (>= (i id) (i (get v 0))) (<= (i id) (i (get v 1)))))))



(defn checkfresh [id] (count (filter identity (map (freshtest id) fresh))))


(printf "%s\nFresh: %s\nAvail: %s\n" alltext fresh avail)

(printf "result %d \n"
        (count (filter (fn [x] (not (= 0 x))) (map checkfresh avail))))
