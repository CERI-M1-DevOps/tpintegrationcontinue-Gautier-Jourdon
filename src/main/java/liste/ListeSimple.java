package liste;

public class ListeSimple {
    private long size;
    Noeud tete;

    public long getSize() {
        return size;
    }

    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    // Renvoie le précédent de 'cible' ou null si tête / introuvable
    private Noeud getPrecedent(Noeud cible) {
        if (tete == null || cible == null || cible == tete) return null;
        Noeud courant = tete;
        while (courant != null && courant.getSuivant() != cible) {
            courant = courant.getSuivant();
        }
        return courant; // peut être null si non trouvé
    }

    // Échange deux nœuds de la liste (ré-adresse les pointeurs)
    public void echanger(Noeud a, Noeud b) {
        if (a == null || b == null || a == b || tete == null) return;

        Noeud prevA = (a == tete) ? null : getPrecedent(a);
        Noeud prevB = (b == tete) ? null : getPrecedent(b);

        // Vérifie que 'a' et 'b' appartiennent à cette liste
        boolean presentA = (a == tete) || (prevA != null);
        boolean presentB = (b == tete) || (prevB != null);
        if (!presentA || !presentB) return;

        Noeud aNext = a.getSuivant();
        Noeud bNext = b.getSuivant();

        // Relie les précédents vers les nouveaux nœuds
        if (prevA != null) prevA.setSuivant(b); else tete = b;
        if (prevB != null) prevB.setSuivant(a); else tete = a;

        // Cas adjacents vs cas généraux
        if (aNext == b) {            // a avant b
            b.setSuivant(a);
            a.setSuivant(bNext);
        } else if (bNext == a) {     // b avant a
            a.setSuivant(b);
            b.setSuivant(aNext);
        } else {                     // non adjacents
            a.setSuivant(bNext);
            b.setSuivant(aNext);
        }
    }

}