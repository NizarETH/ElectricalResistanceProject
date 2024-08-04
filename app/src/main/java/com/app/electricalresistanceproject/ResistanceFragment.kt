package com.app.electricalresistanceproject


import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog

class ResistanceFragment : Fragment() {

    private lateinit var voltageEditText: EditText
    private lateinit var currentEditText: EditText
    private lateinit var calculateButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_resistance, container, false)

        voltageEditText = view.findViewById(R.id.voltageEditText)
        currentEditText = view.findViewById(R.id.currentEditText)
        calculateButton = view.findViewById(R.id.calculateButton)

        calculateButton.setOnClickListener {
            hideKeyboard() //fermer le clavier
            clearFocus()  // retirer le focus
            calculateResistance()
        }

        return view
    }

    private fun calculateResistance() {
        val voltage = voltageEditText.text.toString().toDoubleOrNull()
        val current = currentEditText.text.toString().toDoubleOrNull()

        if (voltage != null && current != null && current != 0.0) {
            val resistance = voltage / current
            showResultDialog(resistance)
        } else {
            showErrorDialog()
        }
    }
    private fun hideKeyboard() {
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }
    private fun showResultDialog(resistance: Double) {
        //afficher 2 chiffres après la virgule
        val formattedResistance = String.format("%.2f", resistance)
        AlertDialog.Builder(requireContext())
            .setTitle("Résistance Calculée")
            .setMessage("La résistance est : $formattedResistance Ω")
            .setPositiveButton("OK") { _, _ ->
                //vider les champs
                voltageEditText.setText("")
                currentEditText.setText("")
            }
            .show()
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Erreur")
            .setMessage("Veuillez entrer des valeurs valides pour la tension et le courant.")
            .setPositiveButton("OK",null)
            .show()
    }
    private fun clearFocus() {
        voltageEditText.clearFocus()
        currentEditText.clearFocus()
    }
}