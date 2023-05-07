
// Copyright (C) 2021, Andes Technology Corp. Confidential Proprietary
import java.util.*;

public class PSSSetVal extends PSSVal {

    Set<PSSVal> m_set = new HashSet<PSSVal>();

	public PSSSetVal() {
	}

	public void add(PSSVal item) {
		m_set.add(item);
	}

    public void insert(PSSVal elem) {
        m_set.add(elem);
    }

    public Set<PSSVal> getSet() {
        return m_set;
    }

	public boolean isEmpty() {
		return (m_set.size() == 0);
	}

    @Override
    public PSSBoolVal Equal(PSSVal rhs) {
        return new PSSBoolVal(this.equals(rhs));
    }

    @Override
    public PSSBoolVal NotEqual(PSSVal rhs) {
        return this.Equal(rhs).LogicalNot();
    }

    @Override
	public PSSBoolVal InRange(PSSVal lhs) {
        for (PSSVal item: m_set) {
			if (item.InRange(lhs).toBool()) {
				return new PSSBoolVal(true);
			}
		}
		return new PSSBoolVal(false);
	}

    @Override
    public String getText() {
        List<String> strs = new ArrayList<String>();
        for (PSSVal elem: m_set)
            strs.add(elem.getText());
        return "{ " + String.join(", ", strs) + " }";
    }

    @Override
    public int compareTo(PSSVal o) {
        if (o instanceof PSSSetVal) {
            PSSSetVal s = (PSSSetVal) o;
            int c = Integer.valueOf(m_set.size()).compareTo(Integer.valueOf(s.m_set.size()));
            if (c != 0)
                return c;
            List<PSSVal> myset = new ArrayList<PSSVal>(m_set);
            List<PSSVal> oset = new ArrayList<PSSVal>(s.m_set);
            Collections.sort(myset);
            Collections.sort(oset);
            for (int i = 0; i < myset.size(); i++) {
                c = myset.get(i).compareTo(oset.get(i));
                if (c != 0)
                    return c;
            }
            return 0;
        }
        return super.compareTo(o);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PSSSetVal) {
            PSSSetVal s = (PSSSetVal) o;
            return (m_set.size() == s.m_set.size()) && m_set.equals(s.m_set);
        }
        return false;
    }

}