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

    // Renvoie le précédent du nœud cible (ou null si tête, introuvable, ou liste vide)
    private Noeud getPrecedent(Noeud cible) {
        if (cible == null || tete == null || cible == tete) {
            return null;
        }
        Noeud courant = tete;
        while (courant != null && courant.getSuivant() != cible) {
            courant = courant.getSuivant();
        }
        return courant; // peut être null si cible non trouvée
    }

    // Helper: premier nœud portant la valeur donnée
    private Noeud trouverPremier(int valeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == valeur) {
                return courant;
            }
            courant = courant.getSuivant();
        }
        return null;
    }

    // Échange les valeurs de deux nœuds (plus robuste que manipuler les liens)
    public void echanger(int v1, int v2) {
        if (v1 == v2) return;
        Noeud n1 = trouverPremier(v1);
        Noeud n2 = trouverPremier(v2);
        if (n1 == null || n2 == null) return;

        int tmp = n1.getElement();
        n1.setElement(n2.getElement());
        n2.setElement(tmp);
    }

}