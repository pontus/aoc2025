
(require '[clojure.repl :refer :all])

(defn get-input [] (let [r (clojure.java.io/reader "input.txt")] r))


(defn charnum [a] (- a 48))


(defn index
  [element list]
  (printf "Finding %s in %s : %d\n\n"
          element
          list
          (.indexOf (seq list) element))
  (.indexOf (seq list) element))

(defn vectorwithout
  [id v]
  (if (<= id 0)
    (subvec v 1)
    (if (< id (count v)) (concat (subvec v 0 id) (subvec v (inc id))))))

(defn linetonums [l] (vec (map charnum (map int (char-array l)))))

(defn highestinvector
  [v]
  (let [highest (apply max v)
        highestpos (index highest v)
        remaining (vectorwithout highestpos v)]
    (vector highest highestpos remaining)))


(defn getmultiplier [togo] (long (Math/pow 10 (dec togo))))

(defn getmax
  [v togo]
  (printf "getmax called with %s       %d\n" v togo)
  (let [resp (if (= togo 1)
               (let [resp (get (highestinvector v) 0)] ; Last one, pick
                                                       ; higest
                 ; remainder
                 (printf "final recursion gave %d\n" resp)
                 resp)
               (let [checkforkhighest (subvec v 0 (inc (- (count v) togo)))
                     hiv (highestinvector checkforkhighest)
                     nextcheck (subvec v (inc (get hiv 1)))]
                 (+ (* (getmultiplier togo) (get hiv 0))
                    (do (printf "recursing %s %d\n" nextcheck togo)
                        (getmax nextcheck (dec togo))))))]
    (printf "max from %s is %d\n" v resp)
    resp))


(defn maxline [line] (getmax (linetonums line) 12))

(defn maxtotal
  [input]
  (let [linemaxes (map maxline input)] (apply + linemaxes)))


(printf "%d\n\n" (maxtotal (line-seq (get-input))))

