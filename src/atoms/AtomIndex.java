package Atoms;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import Helpers.LogHelper;

public class AtomIndex {
	//We assume read numbers is vastly larger than write numbers. 
	private final CopyOnWriteArrayList<AtomIndexNode> atomRef;

	public AtomIndex(ArrayList<Atom> atoms) {
		ArrayList<AtomIndexNode> atomref = new ArrayList<AtomIndexNode>();
		for(Atom atom: atoms){
			atomref.add(new AtomIndexNode(atom));
		}
		this.atomRef = new CopyOnWriteArrayList<AtomIndexNode>(atomref);
	}

	public synchronized int addAtom(Atom atom) {
		AtomIndexNode atomIndexNode = new AtomIndexNode(atom);
		this.atomRef.add(atomIndexNode);
		return this.atomRef.size() - 1;
	}

	public Atom get(int id){
		AtomIndexNode temp = this.atomRef.get(id);
		for(; temp != null; temp = temp.next){
			if(temp.next == null){
				break;
			}
			if(temp.next.atom == null){
				break;
			}
		}
		if(temp == null){
			LogHelper.warn("Atom Index refernce out of boundary. ");
			return null;
		}
		if(temp.atom == null){
			LogHelper.warn("This Atom is downgraded. The ball is not supposed nor permitted to exe this Atom. ");
			return null;
		}
		return temp.atom;
	}

	class AtomIndexNode {
		private Atom atom;
		private AtomIndexNode next = null;

		private AtomIndexNode(Atom atom) {
			this.atom = atom;
		}

		private void upgrade(AtomIndexNode nextAtomIndexNode) {
			if(nextAtomIndexNode == null){
				LogHelper.warn("Trying to upgrade with a NULL REFERENCE");
				return;
			}
			this.next = nextAtomIndexNode;
			LogHelper.debug("Upgrade " + this.atom + "with " + nextAtomIndexNode.atom);
		}
		
		private void downgrade(){
			LogHelper.debug("Downgrade " + this.atom);
			this.atom = null;
		}
	}
}
